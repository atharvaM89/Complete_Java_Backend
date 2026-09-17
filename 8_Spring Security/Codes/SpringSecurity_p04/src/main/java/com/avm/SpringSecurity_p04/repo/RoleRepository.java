package com.avm.SpringSecurity_p04.repo;

import com.avm.SpringSecurity_p04.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByName(String name);


}
