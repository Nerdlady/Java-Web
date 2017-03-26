package com.cardealer.repositories;

import com.cardealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> getAllByMakeOrderByModelAscTravelledDistanceDesc(String make);
    List<Car> getAllByOrderByModelAscTravelledDistanceDesc();
    Car findFirstByMake(String make);
}
