package com.residentEvil.repositories;

import com.residentEvil.entities.Capital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CapitalRepository extends JpaRepository<Capital,Long> {
    @Query("SELECT c.name FROM Capital AS c")
    List<String> getAllCapitalsNames();

    Set<Capital> findAllByNameIn(String[] names);
}
