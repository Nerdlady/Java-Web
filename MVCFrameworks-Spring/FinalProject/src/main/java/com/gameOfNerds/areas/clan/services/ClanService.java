package com.gameOfNerds.areas.clan.services;

import com.gameOfNerds.areas.clan.models.bindingModels.ClanModel;
import com.gameOfNerds.areas.clan.models.bindingModels.CreateClanModel;
import com.gameOfNerds.areas.clan.models.viewModels.ClanViewModel;

import java.util.List;

public interface ClanService {
    List<ClanViewModel> getAllClans();
    ClanModel persis(CreateClanModel clanModel);
    ClanViewModel getById(Long id);
    ClanModel getClanById(Long id);
    ClanModel getByOwnerId(Long id);
}
