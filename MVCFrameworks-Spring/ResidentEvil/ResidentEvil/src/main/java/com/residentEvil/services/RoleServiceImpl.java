package com.residentEvil.services;

import com.residentEvil.entities.Role;
import com.residentEvil.models.bindlingModels.RoleModel;
import com.residentEvil.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RoleModel> getAll() {
        List<Role> roles = this.roleRepository.findAll();
        List<RoleModel> roleModels = new ArrayList<>();
        for (Role role : roles) {
            RoleModel roleModel = this.modelMapper.map(role, RoleModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }

    @Override
    public Set<RoleModel> getAllByName(String[] names) {
        Set<Role> roles = this.roleRepository.findAllByAuthorityIn(names);
        Set<RoleModel> roleModels = new HashSet<>();
        for (Role role : roles) {
            RoleModel roleModel = this.modelMapper.map(role, RoleModel.class);
            roleModels.add(roleModel);
        }
        return roleModels;
    }
}
