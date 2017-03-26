package com.residentEvil.services;

import com.residentEvil.models.bindlingModels.CapitalModel;

import java.util.List;
import java.util.Set;

public interface CapitalService {
    List<String> getCapitalsNames();

    Set<CapitalModel> getAllByName(String[] names);
}
