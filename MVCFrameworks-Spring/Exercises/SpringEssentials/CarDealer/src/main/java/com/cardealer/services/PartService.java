package com.cardealer.services;

import com.cardealer.models.bindingModels.part.AddPartModel;
import com.cardealer.models.bindingModels.part.EditPartModel;
import com.cardealer.models.bindingModels.part.PartModel;
import com.cardealer.models.viewModels.part.PartView;

import java.util.List;

public interface PartService {
    void persist(AddPartModel partModel);
    List<PartView> getAll();
    EditPartModel getById(Long id);
    void update(EditPartModel partModel);
    PartView getViewById(Long id);
    void delete(EditPartModel id);
    PartModel getByName(String name);
}
