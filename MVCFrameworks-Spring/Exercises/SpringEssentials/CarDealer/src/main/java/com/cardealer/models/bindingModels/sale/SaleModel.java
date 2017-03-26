package com.cardealer.models.bindingModels.sale;

import com.cardealer.models.bindingModels.car.RelatedCarModel;
import com.cardealer.models.bindingModels.customer.RelatedCustomerModel;

public class SaleModel {
    private Float discount;
    private RelatedCustomerModel customer;
    private RelatedCarModel car;

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public RelatedCustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(RelatedCustomerModel customer) {
        this.customer = customer;
    }

    public RelatedCarModel getCar() {
        return car;
    }

    public void setCar(RelatedCarModel car) {
        this.car = car;
    }
}
