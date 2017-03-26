package com.cardealer.services;

import com.cardealer.entities.Car;
import com.cardealer.models.bindingModels.car.CarModel;
import com.cardealer.models.bindingModels.car.RelatedCarModel;
import com.cardealer.models.viewModels.car.CarInfoView;
import com.cardealer.models.viewModels.car.CarView;
import com.cardealer.models.viewModels.car.CarWithPartsView;
import com.cardealer.repositories.CarRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    public void persist(CarModel carModel) {
        ModelMapper modelMapper = new ModelMapper();
        Car car = modelMapper.map(carModel, Car.class);
        this.carRepository.saveAndFlush(car);

    }

    @Override
    public List<CarInfoView> getAllByMake(String make) {
        List<Car> cars = new ArrayList<>();
        if (make != null){
            cars = this.carRepository.getAllByMakeOrderByModelAscTravelledDistanceDesc(make);
        } else {
            cars = this.carRepository.getAllByOrderByModelAscTravelledDistanceDesc();
        }

        List<CarInfoView> carInfoViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Car car : cars) {
            CarInfoView carInfoView = modelMapper.map(car,CarInfoView.class);
            carInfoViews.add(carInfoView);
        }
        return carInfoViews;
    }

    @Override
    public CarWithPartsView getById(Long id) {
        Car car = this.carRepository.findOne(id);
        ModelMapper modelMapper = new ModelMapper();
        CarWithPartsView carWithPartsView = null;
        if (car != null){
            carWithPartsView = modelMapper.map(car,CarWithPartsView.class);
        }
        return carWithPartsView;
    }

    @Override
    public List<CarView> getAll() {
        List<Car> cars = this.carRepository.findAll();
        List<CarView> carViews = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for (Car car : cars) {
            CarView carView = modelMapper.map(car,CarView.class);
            carViews.add(carView);
        }
        return carViews;
    }

    @Override
    public RelatedCarModel getByMake(String name) {
        Car car = this.carRepository.findFirstByMake(name);
        ModelMapper modelMapper = new ModelMapper();
        RelatedCarModel relatedCarModel = null;
        if (car != null){
            relatedCarModel = modelMapper.map(car,RelatedCarModel.class);
        }
        return relatedCarModel;
    }
}
