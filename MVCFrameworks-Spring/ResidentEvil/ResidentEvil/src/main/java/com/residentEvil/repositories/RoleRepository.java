package com.residentEvil.repositories;

import com.residentEvil.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Set<Role> findAllByAuthorityIn(String[] names);
}
