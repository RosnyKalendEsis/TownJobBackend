package com.upl.townjob.repository;

import com.upl.townjob.model.Candidacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface CandidacyRepository extends JpaRepository<Candidacy, UUID> {
}
