package com.upl.townjob.repository;

import com.upl.townjob.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User>findUserByMailAndPassword(String mail, String password);
}
