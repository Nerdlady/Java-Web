package com.cardealer.models.bindingModels.car;

import com.cardealer.models.bindingModels.part.PartModel;

import java.util.Set;

public class CarModel {
    private String make;
    private String model;
    private Double travelledDistance;
    private Set<PartModel> parts;

    public CarModel() {
    }

    public CarModel(String make, String model, Double travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(Double travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    public Set<PartModel> getParts() {
        return parts;
    }

    public void setParts(Set<PartModel> parts) {
        this.parts = parts;
    }
}
