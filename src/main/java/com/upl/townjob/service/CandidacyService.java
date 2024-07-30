package com.upl.townjob.service;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.model.Status;
import com.upl.townjob.model.User;
import com.upl.townjob.repository.CandidacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidacyService {
    private final CandidacyRepository candidacyRepository;

    public Candidacy createCandidacy(Candidacy candidacy){
        return  candidacyRepository.save(candidacy);
    }

    public Candidacy saveCandidacy(Candidacy candidacy, MultipartFile cvFile) throws IOException {
        if (cvFile != null && !cvFile.isEmpty()) {
            candidacy.setCv(cvFile.getBytes());
        }
        return candidacyRepository.save(candidacy);
    }

    public Candidacy retrieveCandidacy(UUID id){
        if(candidacyRepository.existsById(id)){
            return candidacyRepository.findById(id).get();
        }
        return null;
    }

    public Candidacy updateCandidacy(UUID id, Candidacy candidacy){
        if(candidacyRepository.existsById(id)){
            Candidacy existCandidacy = candidacyRepository.findById(id).get();
            existCandidacy.setMotivation(candidacy.getMotivation());
            existCandidacy.setMail(candidacy.getMail());
            existCandidacy.setName(candidacy.getName());
            return candidacyRepository.save(candidacy);
        }
        return  null;
    }

    public Candidacy acceptCandidacy(UUID id){
        if(candidacyRepository.existsById(id)){
            Candidacy candidacy = candidacyRepository.findById(id).get();
            candidacy.setStatus(Status.ACCEPTED);
            return candidacyRepository.save(candidacy);
        }
        return null;
    }

    public Candidacy refuseCandidacy(UUID id){
        if(candidacyRepository.existsById(id)){
            Candidacy candidacy = candidacyRepository.findById(id).get();
            candidacy.setStatus(Status.REFUSED);
            return candidacyRepository.save(candidacy);
        }
        return null;
    }

    public List<Candidacy>retrieveAllCandidaciesByUser(User user){
        return candidacyRepository.findAllByUser(user);
    }

    public List<Candidacy>retrieveAllCandidaciesByJobOffer(JobOffer jobOffer){
        return candidacyRepository.findAllByJobOffer(jobOffer);
    }

    public List<Candidacy>retrieveAllCandidaciesByJobOfferCompany(UUID id){
        return candidacyRepository.findAllByJobOffer_Company_Id(id);
    }

    public List<Candidacy>retrieveAllCandidacies(){
        return candidacyRepository.findAll();
    }
}
