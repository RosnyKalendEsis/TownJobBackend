package com.upl.townjob.service;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.Company;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.repository.JobOfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JobOfferService {
    private final JobOfferRepository jobOfferRepository;
    public JobOffer createJobOffer(JobOffer jobOffer){
        return jobOfferRepository.save(jobOffer);
    }

    public JobOffer retrieveJobOffer(UUID id){
        return jobOfferRepository.findById(id).orElse(null);
    }

    public List<JobOffer> retrievesAllJobOffers(){
        return jobOfferRepository.findAll();
    }

    public List<JobOffer> retrieveAllJobOfferByCompany(Company company){
        return jobOfferRepository.findAllByCompany(company);
    }
}
