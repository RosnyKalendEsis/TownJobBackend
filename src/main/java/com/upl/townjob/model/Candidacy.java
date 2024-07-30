package com.upl.townjob.model;

import com.upl.townjob.record.CandidacyDto;
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

    @Column(columnDefinition = "LONGBLOB")
    @Lob
    private byte[] cv;

    @ManyToOne
    private JobOffer jobOffer;
    private Status status;

    public CandidacyDto candidacyDto(){
        return new CandidacyDto(id,name,mail,motivation,user,jobOffer,status);
    }
}
