package com.upl.townjob.controller;

import com.upl.townjob.model.Candidacy;
import com.upl.townjob.service.CandidacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/candidacies")
@RequiredArgsConstructor
public class CandidacyController {

    private final CandidacyService candidacyService;

    @PostMapping
    public ResponseEntity<Candidacy> createCandidacy(@RequestBody Candidacy candidacy) {
        Candidacy createdCandidacy = candidacyService.createCandidacy(candidacy);
        return ResponseEntity.ok(createdCandidacy);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Candidacy> retrieveCandidacy(@PathVariable UUID id) {
        Candidacy candidacy = candidacyService.retrieveCandidacy(id);
        return candidacy != null ? ResponseEntity.ok(candidacy) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Candidacy> updateCandidacy(@PathVariable UUID id, @RequestBody Candidacy candidacy) {
        Candidacy updatedCandidacy = candidacyService.updateCandidacy(id, candidacy);
        return updatedCandidacy != null ? ResponseEntity.ok(updatedCandidacy) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/accept")
    public ResponseEntity<Candidacy> acceptCandidacy(@PathVariable UUID id) {
        Candidacy acceptedCandidacy = candidacyService.acceptCandidacy(id);
        return acceptedCandidacy != null ? ResponseEntity.ok(acceptedCandidacy) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/refuse")
    public ResponseEntity<Candidacy> refuseCandidacy(@PathVariable UUID id) {
        Candidacy refusedCandidacy = candidacyService.refuseCandidacy(id);
        return refusedCandidacy != null ? ResponseEntity.ok(refusedCandidacy) : ResponseEntity.notFound().build();
    }
}
