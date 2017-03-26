package com.residentEvil.services;

import com.residentEvil.entities.Capital;
import com.residentEvil.models.bindlingModels.CapitalModel;
import com.residentEvil.repositories.CapitalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CapitalServiceImpl implements CapitalService {
    private final CapitalRepository capitalRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public CapitalServiceImpl(CapitalRepository capitalRepository, ModelMapper modelMapper) {
        this.capitalRepository = capitalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<String> getCapitalsNames() {
        return this.capitalRepository.getAllCapitalsNames();
    }

    @Override
    public Set<CapitalModel> getAllByName(String[] names) {
        Set<Capital> capitals = this.capitalRepository.findAllByNameIn(names);
        Set<CapitalModel> capitalModels = new HashSet<>();
        for (Capital capital : capitals) {
            CapitalModel capitalModel = this.modelMapper.map(capital,CapitalModel.class);
            capitalModels.add(capitalModel);
        }

        return capitalModels;
    }
}
