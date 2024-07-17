package com.upl.townjob.controller;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.model.User;
import com.upl.townjob.record.UserRecord;
import com.upl.townjob.service.JobOfferService;
import com.upl.townjob.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JobOfferService jobOfferService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRecord record) {
        User user = new User();
        user.setName(record.name());
        user.setMail(record.mail());
        user.setUsername(record.username());
        user.setPassword(record.password());
        user.setCandidacies(new ArrayList<>());
        user.setSavedJobOffers(new ArrayList<>());
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> retrieveUser(@PathVariable UUID id) {
        User user = userService.retrieveUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return updatedUser != null ? ResponseEntity.ok(updatedUser) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam String mail, @RequestParam String password) {
        User user = userService.loginUser(mail, password);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.status(401).build();
    }

    @GetMapping("/{id}/candidacies")
    public ResponseEntity<List<Candidacy>> retrieveUserCandidacies(@PathVariable UUID id) {
        List<Candidacy> candidacies = userService.retrieveUserCandidacies(id);
        return candidacies != null ? ResponseEntity.ok(candidacies) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/savedJobOffers")
    public ResponseEntity<List<JobOffer>> retrieveUserSavedJobOffers(@PathVariable UUID id) {
        List<JobOffer> jobOffers = userService.retrieveUserSavedJobOffers(id);
        return jobOffers != null ? ResponseEntity.ok(jobOffers) : ResponseEntity.notFound().build();
    }

    @PostMapping("/savedJobOffers")
    public ResponseEntity<User> saveJobOffer(@RequestParam UUID user, @RequestParam UUID jobOffer) {
        JobOffer jobOffer1 = jobOfferService.retrieveJobOffer(jobOffer);
        if(jobOffer1 != null){
            User existUser = userService.saveJobOffer(user, jobOffer1);
            return existUser != null ? ResponseEntity.ok(existUser) : ResponseEntity.notFound().build();
        }
        return ResponseEntity.notFound().build();
    }
}