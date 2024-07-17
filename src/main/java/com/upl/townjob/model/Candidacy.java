package com.upl.townjob.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Candidacy implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String mail;
    private String motivation;
    @ManyToOne
    private User user;
    @ManyToOne
    private JobOffer jobOffer;
    private Status status;
}
