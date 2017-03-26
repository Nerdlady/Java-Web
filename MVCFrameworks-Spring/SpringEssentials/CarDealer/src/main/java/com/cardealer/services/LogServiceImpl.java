package com.cardealer.services;

import com.cardealer.entities.Log;
import com.cardealer.models.bindingModels.log.LogModel;
import com.cardealer.models.viewModels.log.LogView;
import com.cardealer.repositories.LogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LogServiceImpl implements LogService {
    @Autowired
    private LogRepository logRepository;

    @Override
    public void persist(LogModel logModel) {
        ModelMapper modelMapper = new ModelMapper();
        Log log = modelMapper.map(logModel, Log.class);
        this.logRepository.saveAndFlush(log);
    }

    @Override
    public List<LogView> getAllByUsername(String username) {
        List<Log> logs = new ArrayList<>();
        if (username != null){
            logs = this.logRepository.findByUserUsernameOrderByDateAsc(username);
        } else {
            logs = this.logRepository.findAllByOrderByDateAsc();
        }

        List<LogView> logViews = new ArrayList<>();
        ModelMapper modelMapper= new ModelMapper();
        for (Log log : logs) {
            LogView logView = modelMapper.map(log,LogView.class);
            logViews.add(logView);
        }
        return logViews;
    }

    @Override
    public void deleteAllLogs() {
        this.logRepository.deleteAll();
    }


}
