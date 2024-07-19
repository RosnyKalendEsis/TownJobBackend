package com.upl.townjob.controller;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.Company;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.service.CompanyService;
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
    private final CompanyService companyService;

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

    @GetMapping("/{id}/company")
    public ResponseEntity<List<JobOffer>> retrieveJobOfferCompany(@PathVariable UUID id) {
        Company company = companyService.retrieveCompany(id);
        return company != null ? ResponseEntity.ok(jobOfferService.retrieveAllJobOfferByCompany(company)) : ResponseEntity.notFound().build();
    }
}