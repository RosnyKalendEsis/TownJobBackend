package com.upl.townjob.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JobOffer implements Serializable {
    @Id
    private UUID id;
    private String title;
    private String description;
    @OneToOne
    private Company company;
    @OneToMany
    private List<Candidacy> candidacies;

}
