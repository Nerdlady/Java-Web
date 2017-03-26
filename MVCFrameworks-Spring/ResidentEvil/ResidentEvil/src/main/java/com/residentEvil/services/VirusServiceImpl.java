package com.residentEvil.services;

import com.residentEvil.entities.Capital;
import com.residentEvil.entities.Virus;
import com.residentEvil.models.bindlingModels.EditVirusModel;
import com.residentEvil.models.bindlingModels.VirusModel;
import com.residentEvil.models.viewModels.ModifiableVirusView;
import com.residentEvil.models.viewModels.VirusView;
import com.residentEvil.repositories.VirusRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

@Service
public class VirusServiceImpl implements VirusService {
    private final VirusRepository virusRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public VirusServiceImpl(VirusRepository virusRepository, ModelMapper modelMapper) {
        this.virusRepository = virusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void persist(VirusModel virusModel) {
        Virus virus = this.modelMapper.map(virusModel, Virus.class);
        this.virusRepository.save(virus);
    }

    @Override
    public List<VirusView> getAll() {
        List<Virus> viruses = this.virusRepository.findAll();
        List<VirusView> virusViews = new ArrayList<>();
        for (Virus virus : viruses) {
            VirusView virusView = this.modelMapper.map(virus, VirusView.class);
            virusViews.add(virusView);
        }
        return virusViews;
    }

    @Override
    public ModifiableVirusView getById(Long id) {
        Virus virus = this.virusRepository.findOne(id);
        ModifiableVirusView modifiableVirusView = null;
        if (virus != null) {
            modifiableVirusView = this.modelMapper.map(virus, ModifiableVirusView.class);
        }
        return modifiableVirusView;
    }

    @Override
    public void update(EditVirusModel editVirusModel) {
        Virus virus = this.modelMapper.map(editVirusModel, Virus.class);

        this.virusRepository.save(virus);
    }

    @Override
    public void delete(Long id) {
        this.virusRepository.delete(id);
    }

    @Transactional
    @Override
    public String getGeoData() {
        List<Virus> viruses = this.virusRepository.findAll();
        StringBuilder geoJson = new StringBuilder();
        String header = "{\n" +
                "    \"type\": \"FeatureCollection\",\n" +
                "    \"features\": [\n";
        String footer = "]\n" +
                "}\n";
        geoJson.append(header);
        StringJoiner joiner = new StringJoiner(",");
        for (Virus virus : viruses) {
            String color = "#f00";
            int magnitude = 0;
            switch (virus.getMagnitude()) {
                case LOW:
                    magnitude = 4;
                    break;
                case MEDIUM:
                    magnitude = 5;
                    break;
                case HIGH:
                    magnitude = 6;
                    break;
            }

            for (Capital capital : virus.getCapitals()) {
                String body = String.format("{\n" +
                        "        \"type\": \"Feature\",\n" +
                        "        \"properties\": {\n" +
                        "            \"mag\": %d,\n" +
                        "            \"color\": \"%s\"\n" +
                        "        },\n" +
                        "        \"geometry\": {\n" +
                        "            \"type\": \"Point\",\n" +
                        "            \"coordinates\": [\n" +
                        "                %f,\n" +
                        "                %f\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    }\n", magnitude, color, capital.getLatitude(), capital.getLongitude());
                joiner.add(body);
            }
        }

        geoJson.append(joiner);
        geoJson.append(footer);

        return geoJson.toString();
    }
}
