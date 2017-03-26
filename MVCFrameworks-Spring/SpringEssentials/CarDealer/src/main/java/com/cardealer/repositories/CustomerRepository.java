package com.cardealer.repositories;

import com.cardealer.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> getAllByOrderByBirthDateAscIsYoungDriverAsc();

    List<Customer> getAllByOrderByBirthDateDescIsYoungDriverAsc();

    Customer findFirstByName(String name);
}
