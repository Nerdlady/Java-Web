package com.gameOfNerds.areas.clan.services;

import com.gameOfNerds.areas.clan.entities.Clan;
import com.gameOfNerds.areas.clan.models.bindingModels.ClanModel;
import com.gameOfNerds.areas.clan.models.bindingModels.CreateClanModel;
import com.gameOfNerds.areas.clan.models.viewModels.ClanViewModel;
import com.gameOfNerds.areas.clan.repositories.ClanRepository;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClanServiceImpl implements ClanService {
    private final ClanRepository clanRepository;
    private final ModelMapper modelMapper;
    private final UserGameInfoService userGameInfoService;

    @Autowired
    public ClanServiceImpl(ClanRepository clanRepository, ModelMapper modelMapper, UserGameInfoService userGameInfoService) {
        this.clanRepository = clanRepository;
        this.modelMapper = modelMapper;
        this.userGameInfoService = userGameInfoService;
    }

    @Override
    public List<ClanViewModel> getAllClans() {
        List<Clan> clans = this.clanRepository.findAllByOrderByTotalScoreDesc();
        List<ClanViewModel> clanViewModels = new ArrayList<>();
        for (Clan clan : clans) {
            ClanViewModel clanViewModel = this.modelMapper.map(clan,ClanViewModel.class);
            clanViewModels.add(clanViewModel);
        }
        return clanViewModels;
    }

    @Override
    public ClanModel persis(CreateClanModel clanModel) {
        Clan clan = this.modelMapper.map(clanModel,Clan.class);
        clan = this.clanRepository.saveAndFlush(clan);
        clan.setTotalScore(0D);
        return this.modelMapper.map(clan,ClanModel.class);
    }

    @Override
    public ClanViewModel getById(Long id) {
        Clan clan = this.clanRepository.findOne(id);
        ClanViewModel viewModel = null;
        if (clan != null){
            viewModel = this.modelMapper.map(clan,ClanViewModel.class);
        }
        return viewModel;
    }

    @Override
    public ClanModel getClanById(Long id) {
        Clan clan = this.clanRepository.findOne(id);
        ClanModel clanModel = null;
        if (clan != null){
            clanModel = this.modelMapper.map(clan,ClanModel.class);
        }
        return clanModel;
    }

    @Override
    public ClanModel getByOwnerId(Long id) {
        Clan clan = this.clanRepository.findByOwnerId(id);
        ClanModel clanModel = null;
        if (clan != null){
            clanModel = this.modelMapper.map(clan,ClanModel.class);
        }
        return clanModel;
    }
}
