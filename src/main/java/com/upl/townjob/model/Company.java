package com.upl.townjob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Company implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String mail;
    private String password;
    @OneToMany
    private List<JobOffer> jobOffers;
}