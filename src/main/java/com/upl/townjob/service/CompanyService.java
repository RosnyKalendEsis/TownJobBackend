package com.upl.townjob.service;

import com.upl.townjob.model.Company;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company createCompany(Company company){
        return companyRepository.save(company);
    }

    public Company retrieveCompany(UUID id){
        return companyRepository.findById(id).orElse(null);
    }

    public Company updateCompany(UUID id, Company company){
        if(companyRepository.existsById(id)){
            Company existCompany = companyRepository.findById(id).get();
            existCompany.setMail(company.getMail());
            existCompany.setName(company.getName());
            existCompany.setPassword(company.getPassword());
            existCompany.setJobOffers(company.getJobOffers());
            return companyRepository.save(existCompany);
        }
        return null;
    }

    public List<JobOffer>retrieveCompanyJobOffers(UUID id){
        if(companyRepository.existsById(id)){
            Company company= companyRepository.findById(id).get();
            return company.getJobOffers();
        }
        return null;
    }

    public Company loginCompany(String mail, String password){
        return companyRepository.findCompanyByMailAndPassword(mail, password).orElse(null);
    }

    public Company addJobOfferToCompany(UUID id, JobOffer jobOffer){
        if(companyRepository.existsById(id)){
            Company company = companyRepository.findById(id).get();
            List<JobOffer> jobOffers = company.getJobOffers();
            jobOffers.add(jobOffer);
            company.setJobOffers(jobOffers);
            return  companyRepository.save(company);
        }
        return null;
    }
}
