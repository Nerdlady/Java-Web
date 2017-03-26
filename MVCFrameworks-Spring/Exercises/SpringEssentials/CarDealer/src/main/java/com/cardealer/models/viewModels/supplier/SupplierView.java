package com.cardealer.models.viewModels.supplier;

import com.cardealer.models.viewModels.part.PartView;

import java.util.Set;

public class SupplierView {
    private Long id;
    private String name;
    private Set<PartView> parts;

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

    public Set<PartView> getParts() {
        return parts;
    }

    public void setParts(Set<PartView> parts) {
        this.parts = parts;
    }
}
