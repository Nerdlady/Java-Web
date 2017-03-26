package com.residentEvil.services;

import com.residentEvil.models.bindlingModels.EditVirusModel;
import com.residentEvil.models.bindlingModels.VirusModel;
import com.residentEvil.models.viewModels.ModifiableVirusView;
import com.residentEvil.models.viewModels.VirusView;

import java.util.List;

public interface VirusService {
    void persist(VirusModel virusModel);
    List<VirusView> getAll();

    ModifiableVirusView getById(Long id);

    void update(EditVirusModel editVirusModel);
    void delete(Long id);
    String getGeoData();
}
