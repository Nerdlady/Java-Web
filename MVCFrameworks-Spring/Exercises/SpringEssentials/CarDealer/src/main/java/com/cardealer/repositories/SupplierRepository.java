package com.cardealer.repositories;

import com.cardealer.entities.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    List<Supplier> getAllByImporter(Boolean importer);
    Supplier findFirstByName(String name);
}
