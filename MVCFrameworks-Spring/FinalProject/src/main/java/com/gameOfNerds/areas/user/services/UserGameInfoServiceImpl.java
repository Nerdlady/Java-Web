package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.clan.entities.Clan;
import com.gameOfNerds.areas.user.entities.UserGameInfo;
import com.gameOfNerds.areas.user.models.bindingModels.UserGameInfoModel;
import com.gameOfNerds.areas.user.models.viewModels.UserGameInfoViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UserWithClanModel;
import com.gameOfNerds.areas.user.repositories.UserGameInfoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGameInfoServiceImpl implements UserGameInfoService {
    private final UserGameInfoRepository userGameInfoRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserGameInfoServiceImpl(UserGameInfoRepository userGameInfoRepository, ModelMapper modelMapper) {
        this.userGameInfoRepository = userGameInfoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void persist(UserGameInfoModel userGameInfoModel) {
        UserGameInfo userGameInfo = this.modelMapper.map(userGameInfoModel,UserGameInfo.class);
        userGameInfo.setScore(0D);
        this.userGameInfoRepository.saveAndFlush(userGameInfo);
    }

    @Override
    public List<UserGameInfoViewModel> findAll() {
        List<UserGameInfo> userGameInfos = this.userGameInfoRepository.findAllByOrderByScoreDesc();
        List<UserGameInfoViewModel> userGameInfoViewModels = new ArrayList<>();
        for (UserGameInfo userGameInfo : userGameInfos) {
            UserGameInfoViewModel userGameInfoViewModel = this.modelMapper.map(userGameInfo,UserGameInfoViewModel.class);
            userGameInfoViewModels.add(userGameInfoViewModel);
        }
        return userGameInfoViewModels;
    }

    @Override
    public void givePoints(Long userId, Double points) {
        UserGameInfo user = this.userGameInfoRepository.findByUserId(userId);
        Double currentScore = user.getScore();

        if (user.getClan() != null){
            Double clanScore = user.getClan().getTotalScore();
            user.getClan().setTotalScore(clanScore + points);
        }
        user.setScore(currentScore + points);
        this.userGameInfoRepository.saveAndFlush(user);
    }

    @Override
    public UserGameInfoModel getByUserUsername(String username) {
        UserGameInfo userGameInfo = this.userGameInfoRepository.findByUserUsername(username);
        UserGameInfoModel userGameInfoModel = null;
        if (userGameInfo != null){
            userGameInfoModel = this.modelMapper.map(userGameInfo,UserGameInfoModel.class);
        }
        return userGameInfoModel;
    }

    @Override
    public void setClan(UserGameInfoModel userGameInfoModel) {
        UserGameInfo userGameInfo = this.userGameInfoRepository.findByUserId(userGameInfoModel.getUser().getId());
        Clan clan = null;
        if (userGameInfoModel.getClan() != null){
             clan = this.modelMapper.map(userGameInfoModel.getClan(),Clan.class);
        }
        userGameInfo.setClan(clan);
        this.userGameInfoRepository.saveAndFlush(userGameInfo);

    }

    @Override
    public UserWithClanModel getWithClanByUserUsername(String username){
        UserGameInfo  userGameInfo = this.userGameInfoRepository.findByUserUsername(username);
        UserWithClanModel userWithClanModel = null;
        if (userGameInfo != null){
            userWithClanModel = this.modelMapper.map(userGameInfo,UserWithClanModel.class);
        }
        return userWithClanModel;
    }

    @Override
    public UserGameInfoViewModel getViewByUserId(Long id) {
        UserGameInfo userGameInfo = this.userGameInfoRepository.findByUserId(id);
        UserGameInfoViewModel userGameInfoViewModel = null;
        if (userGameInfo != null){
            userGameInfoViewModel = this.modelMapper.map(userGameInfo,UserGameInfoViewModel.class);
        }
        return userGameInfoViewModel;
    }

    @Override
    public UserGameInfoViewModel getViewByUserUsername(String username) {
        UserGameInfo userGameInfo = this.userGameInfoRepository.findByUserUsername(username);
        UserGameInfoViewModel userGameInfoViewModel = null;
        if (userGameInfo != null){
            userGameInfoViewModel = this.modelMapper.map(userGameInfo,UserGameInfoViewModel.class);
        }
        return userGameInfoViewModel;
    }


}
