package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.entities.Role;
import com.gameOfNerds.areas.user.models.bindingModels.RoleModel;
import com.gameOfNerds.areas.user.repositories.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public RoleModel findByAuthority(String authority) {
        Role role = this.roleRepository.findByAuthority(authority);
        RoleModel roleModel = null;
        if (role != null){
            roleModel = this.modelMapper.map(role,RoleModel.class);
        }
        return roleModel;
    }
}
