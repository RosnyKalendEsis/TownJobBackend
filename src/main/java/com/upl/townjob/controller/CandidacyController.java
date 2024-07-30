package com.upl.townjob.controller;

import com.upl.townjob.model.*;
import com.upl.townjob.record.CandidacyDto;
import com.upl.townjob.record.CandidacyRecord;
import com.upl.townjob.service.CandidacyService;
import com.upl.townjob.service.CompanyService;
import com.upl.townjob.service.JobOfferService;
import com.upl.townjob.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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


    @PostMapping("/submit")
    public ResponseEntity<CandidacyDto> submitCandidacy(
            @RequestParam("name") String name,
            @RequestParam("mail") String mail,
            @RequestParam("motivation") String motivation,
            @RequestParam("userId") UUID userId,
            @RequestParam("jobOfferId") UUID jobOfferId,
            @RequestParam("cvFile") MultipartFile cvFile
    ) throws IOException {
        Candidacy candidacy = new Candidacy();
        candidacy.setName(name);
        candidacy.setMail(mail);
        candidacy.setMotivation(motivation);
        candidacy.setStatus(Status.PENDING);
        // Set user and jobOffer using their respective services
        candidacy.setUser(userService.retrieveUser(userId));
        candidacy.setJobOffer(jobOfferService.retrieveJobOffer(jobOfferId));
        Candidacy savedCandidacy = candidacyService.saveCandidacy(candidacy, cvFile);
        if(savedCandidacy != null){
            return ResponseEntity.ok(savedCandidacy.candidacyDto());
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{id}/cv")
    public ResponseEntity<byte[]> getCandidacyCv(@PathVariable UUID id) {
        Candidacy candidacy = candidacyService.retrieveCandidacy(id);
        if (candidacy != null && candidacy.getCv() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=cv.pdf");
            return new ResponseEntity<>(candidacy.getCv(), headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

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
    public ResponseEntity<CandidacyDto> retrieveCandidacy(@PathVariable UUID id) {
        Candidacy candidacy = candidacyService.retrieveCandidacy(id);
        return candidacy != null ? ResponseEntity.ok(candidacy.candidacyDto()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CandidacyDto> updateCandidacy(@PathVariable UUID id, @RequestBody Candidacy candidacy) {
        Candidacy updatedCandidacy = candidacyService.updateCandidacy(id, candidacy);
        return updatedCandidacy != null ? ResponseEntity.ok(updatedCandidacy.candidacyDto()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<CandidacyDto> acceptCandidacy(@PathVariable UUID id) {
        Candidacy acceptedCandidacy = candidacyService.acceptCandidacy(id);
        return acceptedCandidacy != null ? ResponseEntity.ok(acceptedCandidacy.candidacyDto()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/refuse")
    public ResponseEntity<CandidacyDto> refuseCandidacy(@PathVariable UUID id) {
        Candidacy refusedCandidacy = candidacyService.refuseCandidacy(id);
        return refusedCandidacy != null ? ResponseEntity.ok(refusedCandidacy.candidacyDto()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/job_offer/{id}")
    public ResponseEntity<List<CandidacyDto>>retrieveAllByJobOffer(@PathVariable UUID id){
        JobOffer jobOffer = jobOfferService.retrieveJobOffer(id);
       return jobOffer != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByJobOffer(jobOffer).stream().map(Candidacy::candidacyDto).toList()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/company/{id}")
    public ResponseEntity<List<CandidacyDto>>retrieveAllByJobOfferCompany(@PathVariable UUID id){
        Company company = companyService.retrieveCompany(id);
        return company != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByJobOfferCompany(id).stream().map(Candidacy::candidacyDto).toList()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<CandidacyDto>>retrieveAllByUser(@PathVariable UUID id){
        User user = userService.retrieveUser(id);
        return user != null ? ResponseEntity.ok(candidacyService.retrieveAllCandidaciesByUser(user).stream().map(Candidacy::candidacyDto).toList()) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<CandidacyDto>>retrieveAll(){
        return ResponseEntity.ok(candidacyService.retrieveAllCandidacies().stream().map(Candidacy::candidacyDto).toList());
    }
}
