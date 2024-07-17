package com.upl.townjob.repository;

import com.upl.townjob.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Optional<Company> findCompanyByMailAndPassword(String mail, String password);
}
