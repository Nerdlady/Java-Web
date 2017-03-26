package com.cardealer.services;

import com.cardealer.models.bindingModels.log.LogModel;
import com.cardealer.models.viewModels.log.LogView;

import java.util.List;

public interface LogService {
    void persist(LogModel logModel);
    List<LogView> getAllByUsername(String username);
    void deleteAllLogs();
}
