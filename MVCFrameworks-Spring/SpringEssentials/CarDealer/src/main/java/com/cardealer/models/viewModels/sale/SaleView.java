package com.cardealer.models.viewModels.sale;

import com.cardealer.models.viewModels.car.CarView;
import com.cardealer.models.viewModels.customer.CustomerNameView;

public class SaleView {
    private Long id;
    private Float discount;
    private CarView car;
    private CustomerNameView customer;

    public Float getDiscount() {
        return discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public CarView getCar() {
        return car;
    }

    public void setCar(CarView car) {
        this.car = car;
    }

    public CustomerNameView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerNameView customer) {
        this.customer = customer;
    }
}
