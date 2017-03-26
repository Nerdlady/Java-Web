package com.cardealer.models.bindingModels.part;

import com.cardealer.models.bindingModels.supplier.SupplierModel;

public class AddPartModel {
    private String name;
    private Double price;
    private Integer quantity;
    private SupplierModel supplier;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public SupplierModel getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierModel supplier) {
        this.supplier = supplier;
    }

}
