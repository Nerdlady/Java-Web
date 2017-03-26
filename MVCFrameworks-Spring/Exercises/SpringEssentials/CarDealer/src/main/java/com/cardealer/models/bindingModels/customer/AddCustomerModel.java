package com.cardealer.models.bindingModels.customer;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AddCustomerModel {
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    private Boolean isYoungDriver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getIsYoungDriver() {
        return isYoungDriver;
    }

    public void setIsYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
