package com.upl.townjob.repository;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.model.JobOffer;
import com.upl.townjob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, UUID> {
    List<Candidacy> findAllByUser(User user);
    List<Candidacy>findAllByJobOffer(JobOffer jobOffer);
    List<Candidacy>findAllByJobOffer_Company_Id(UUID id);
}
