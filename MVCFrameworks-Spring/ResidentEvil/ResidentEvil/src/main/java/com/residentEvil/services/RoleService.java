package com.residentEvil.services;

import com.residentEvil.models.bindlingModels.RoleModel;

import java.util.List;
import java.util.Set;

public interface RoleService {
    List<RoleModel> getAll();
    Set<RoleModel> getAllByName(String[] names);
}
