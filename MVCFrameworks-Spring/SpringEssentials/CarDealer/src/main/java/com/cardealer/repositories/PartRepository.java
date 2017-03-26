package com.cardealer.repositories;

import com.cardealer.entities.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PartRepository extends JpaRepository<Part,Long> {
    @Modifying
    @Query("UPDATE Part AS p SET p.price = :price, p.quantity = :quantity WHERE p.id = :id")
    void update(@Param("price")Double price,@Param("quantity") Integer quantity ,@Param("id") Long id);

    Part findByName(String name);
}
