package com.cardealer.services;

import com.cardealer.models.bindingModels.car.CarModel;
import com.cardealer.models.bindingModels.car.RelatedCarModel;
import com.cardealer.models.viewModels.car.CarInfoView;
import com.cardealer.models.viewModels.car.CarView;
import com.cardealer.models.viewModels.car.CarWithPartsView;

import java.util.List;


public interface CarService {
    void persist(CarModel carModel);
    List<CarInfoView> getAllByMake(String make);
    CarWithPartsView getById(Long id);
    List<CarView> getAll();
    RelatedCarModel getByMake(String name);
}
