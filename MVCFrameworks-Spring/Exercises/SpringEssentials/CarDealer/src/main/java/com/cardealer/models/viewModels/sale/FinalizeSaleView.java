package com.cardealer.models.viewModels.sale;

import com.cardealer.models.viewModels.car.CarWithPartsView;
import com.cardealer.models.viewModels.customer.CustomerDriverView;

public class FinalizeSaleView {
    private Float discount;
    private CarWithPartsView car;
    private CustomerDriverView customer;
    private Double carPrice;
    private Double finalCarPrice;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public CarWithPartsView getCar() {
        return car;
    }

    public void setCar(CarWithPartsView car) {
        this.car = car;
    }

    public CustomerDriverView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDriverView customer) {
        this.customer = customer;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getFinalCarPrice() {
        return finalCarPrice;
    }

    public void setFinalCarPrice(Double finalCarPrice) {
        this.finalCarPrice = finalCarPrice;
    }
}
