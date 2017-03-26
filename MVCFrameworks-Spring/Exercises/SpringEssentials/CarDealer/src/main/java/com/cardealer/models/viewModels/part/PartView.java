package com.cardealer.models.viewModels.part;

import com.cardealer.models.viewModels.supplier.SupplierView;

public class PartView {
    private Long id;
    private String name;
    private Double price;

    private Integer quantity;
    private SupplierView supplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public SupplierView getSupplier() {
        return supplier;
    }

    public void setSupplier(SupplierView supplier) {
        this.supplier = supplier;
    }
}
