package com.upl.townjob.service;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.Status;
import com.upl.townjob.repository.CandidacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CandidacyService {
    private final CandidacyRepository candidacyRepository;

    public Candidacy createCandidacy(Candidacy candidacy){
        return  candidacyRepository.save(candidacy);
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
}