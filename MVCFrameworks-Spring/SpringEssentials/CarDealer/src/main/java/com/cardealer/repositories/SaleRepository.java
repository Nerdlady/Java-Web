package com.cardealer.repositories;

import com.cardealer.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    List<Sale> findAllByDiscount(Float discount);

    List<Sale> findAllByDiscountGreaterThan(Float discount);
}
