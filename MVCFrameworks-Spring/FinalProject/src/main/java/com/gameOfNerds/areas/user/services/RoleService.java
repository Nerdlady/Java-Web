package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.models.bindingModels.RoleModel;

public interface RoleService {
    RoleModel findByAuthority(String authority);
}
