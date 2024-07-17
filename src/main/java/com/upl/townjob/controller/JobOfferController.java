package com.upl.townjob.controller;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.service.JobOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/jobOffers")
@RequiredArgsConstructor
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(@RequestBody JobOffer jobOffer) {
        JobOffer createdJobOffer = jobOfferService.createJobOffer(jobOffer);
        return ResponseEntity.ok(createdJobOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> retrieveJobOffer(@PathVariable UUID id) {
        JobOffer jobOffer = jobOfferService.retrieveJobOffer(id);
        return jobOffer != null ? ResponseEntity.ok(jobOffer) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<JobOffer>> retrievesAllJobOffers() {
        List<JobOffer> jobOffers = jobOfferService.retrievesAllJobOffers();
        return ResponseEntity.ok(jobOffers);
    }

    @GetMapping("/{id}/candidacies")
    public ResponseEntity<List<Candidacy>> retrieveJobOfferCandidacies(@PathVariable UUID id) {
        List<Candidacy> candidacies = jobOfferService.retrieveJobOfferCandidacies(id);
        return candidacies != null ? ResponseEntity.ok(candidacies) : ResponseEntity.notFound().build();
    }
}