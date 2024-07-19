package com.upl.townjob.controller;

import com.upl.townjob.model.*;
import com.upl.townjob.record.CandidacyRecord;
import com.upl.townjob.service.CandidacyService;
import com.upl.townjob.service.CompanyService;
import com.upl.townjob.service.JobOfferService;
import com.upl.townjob.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/candidacies")
@RequiredArgsConstructor
public class CandidacyController {

    private final CandidacyService candidacyService;
    private final JobOfferService jobOfferService;
    private final UserService userService;
    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Candidacy> createCandidacy(@RequestBody CandidacyRecord candidacyRecord) {
        User user = userService.retrieveUser(candidacyRecord.userId());
        JobOffer jobOffer = jobOfferService.retrieveJobOffer(candidacyRecord.jobOfferId());
        if(user != null && jobOffer != null){
            Candidacy candidacy = new Candidacy();
            candidacy.setStatus(Status.PENDING);
            candidacy.setName(candidacyRecord.name());
            candidacy.setMail(candidacyRecord.mail());
            candidacy.setMotivation(candidacyRecord.motivation());
            candidacy.setUser(user);
            candidacy.setJobOffer(jobOffer);
            Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
            return ResponseEntity.ok(createdCandidacy);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidacy> retrieveCandidacy(@PathVariable UUID id) {
        Candidacy candidacy = candidacyService.retrieveCandidacy(id);
        return candidacy != null ? ResponseEntity.ok(candidacy) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidacy> updateCandidacy(@PathVariable UUID id, @RequestBody Candidacy candidacy) {
        Candidacy updatedCandidacy = candidacyService.updateCandidacy(id, candidacy);
        return updatedCandidacy != null ? ResponseEntity.ok(updatedCandidacy) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Candidacy> acceptCandidacy(@PathVariable UUID id) {
        Candidacy acceptedCandidacy = candidacyService.acceptCandidacy(id);
        return acceptedCandidacy != null ? ResponseEntity.ok(acceptedCandidacy) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/refuse")
    public ResponseEntity<Candidacy> refuseCandidacy(@PathVariable UUID id) {
        Candidacy refusedCandidacy = candidacyService.refuseCandidacy(id);
        return refusedCandidacy != null ? ResponseEntity.ok(refusedCandidacy) : ResponseEntity.notFound().build();
    }

    @GetMapping("/job_offer/{id}")
    public ResponseEntity<List<Candidacy>>retrieveAllByJobOffer(@PathVariable UUID id){
        JobOffer jobOffer = jobOfferService.retrieveJobOffer(id);
       return jobOffer != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByJobOffer(jobOffer)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<Candidacy>>retrieveAllByJobOfferCompany(@PathVariable UUID id){
        Company company = companyService.retrieveCompany(id);
        return company != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByJobOfferCompany(id)) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Candidacy>>retrieveAllByUser(@PathVariable UUID id){
        User user = userService.retrieveUser(id);
        return user != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByUser(user)) : ResponseEntity.notFound().build();
    }
}
