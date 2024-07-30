package com.upl.townjob.record;

import com.upl.townjob.model.JobOffer;
import com.upl.townjob.model.Status;
import com.upl.townjob.model.User;

import java.util.UUID;

public record CandidacyDto(UUID id,String name,String mail,String motivation,User user,JobOffer jobOffer,Status status) {
}
