package com.cardealer.models.viewModels.customer;

import com.cardealer.models.viewModels.sale.SaleView;

import java.util.Set;

public class CustomerDetailsView {
    private String name;
    private Boolean isYoungDriver;
    private Set<SaleView> sales;
    private Double totalSum;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleView> getSales() {
        return sales;
    }

    public void setSales(Set<SaleView> sales) {
        this.sales = sales;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }
}
