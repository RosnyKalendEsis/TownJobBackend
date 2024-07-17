package com.upl.townjob.repository;

import com.upl.townjob.model.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, UUID> {
}
