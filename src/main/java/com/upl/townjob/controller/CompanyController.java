package com.upl.townjob.controller;

import com.upl.townjob.model.Company;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        Company createdCompany = companyService.createCompany(company);
        return ResponseEntity.ok(createdCompany);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> retrieveCompany(@PathVariable UUID id) {
        Company company = companyService.retrieveCompany(id);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Company> updateCompany(@PathVariable UUID id, @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return updatedCompany != null ? ResponseEntity.ok(updatedCompany) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/jobOffers")
    public ResponseEntity<List<JobOffer>> retrieveCompanyJobOffers(@PathVariable UUID id) {
        List<JobOffer> jobOffers = companyService.retrieveCompanyJobOffers(id);
        return jobOffers != null ? ResponseEntity.ok(jobOffers) : ResponseEntity.notFound().build();
    }

    @PostMapping("/login")
    public ResponseEntity<Company> loginCompany(@RequestParam String mail, @RequestParam String password) {
        Company company = companyService.loginCompany(mail, password);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.status(401).build();
    }

    @PostMapping("/{id}/jobOffers")
    public ResponseEntity<Company> addJobOfferToCompany(@PathVariable UUID id, @RequestBody JobOffer jobOffer) {
        Company company = companyService.addJobOfferToCompany(id, jobOffer);
        return company != null ? ResponseEntity.ok(company) : ResponseEntity.notFound().build();
    }
}