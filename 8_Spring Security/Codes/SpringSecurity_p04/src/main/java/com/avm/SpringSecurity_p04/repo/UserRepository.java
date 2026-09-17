package com.avm.SpringSecurity_p04.repo;

import com.avm.SpringSecurity_p04.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, String> {

    Optional<AppUser> findByUsername(String name);
}
