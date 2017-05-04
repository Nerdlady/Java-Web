package com.gameOfNerds.areas.user.repositories;

import com.gameOfNerds.areas.user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByAuthority(String authority);
}
