package com.gameOfNerds.areas.user.services;

import com.gameOfNerds.areas.user.entities.UsersFight;
import com.gameOfNerds.areas.user.models.bindingModels.fights.AddUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.EditUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.UsersFightModel;
import com.gameOfNerds.areas.user.models.viewModels.UserViewModel;
import com.gameOfNerds.areas.user.models.viewModels.UsersFightViewModel;
import com.gameOfNerds.areas.user.repositories.UsersFightsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersFightServiceImpl implements UsersFightService {
    private final UsersFightsRepository usersFightsRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UsersFightServiceImpl(UsersFightsRepository usersFightsRepository, ModelMapper modelMapper) {
        this.usersFightsRepository = usersFightsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean isUsersInFight(UsersFightModel usersFightModel) {

        UsersFight usersFight = this.usersFightsRepository.getNotFinishedFight(
                usersFightModel.getCurrentUser().getId(),
                usersFightModel.getOtherUser().getId());

        return usersFight != null;
    }

    @Override
    public boolean isCurrentUserAnswerInCurrentFight(UsersFightModel usersFightModel) {
        UsersFight usersFightCurrentUserChallenger = this.usersFightsRepository
                .findByChallengerIdAndChallengedIdAndNotFinished(
                usersFightModel.getCurrentUser().getId(),
                usersFightModel.getOtherUser().getId());

        UsersFight usersFightCurrentUserChallenged = this.usersFightsRepository
                .findByChallengedIdAndChallengerIdAndNotFinished(
                        usersFightModel.getCurrentUser().getId(),
                        usersFightModel.getOtherUser().getId());
        boolean isUserAnswered = false;

        if (usersFightCurrentUserChallenger != null){
            isUserAnswered = usersFightCurrentUserChallenger.getIsChallengerUserAnswered();
        }

        if (usersFightCurrentUserChallenged != null){
            isUserAnswered = usersFightCurrentUserChallenged.getIsChallengedUserAnswered();
        }

        return isUserAnswered;
    }

    @Override
    public EditUsersFightModel save(AddUsersFightModel usersFightModel) {
        UsersFight usersFight = this.modelMapper.map(usersFightModel, UsersFight.class);
        this.usersFightsRepository.saveAndFlush(usersFight);
        return this.modelMapper.map(usersFight, EditUsersFightModel.class);
    }

    @Override
    public EditUsersFightModel getCurrentFight(UsersFightModel usersFightModel) {
        if (usersFightModel == null
                || usersFightModel.getCurrentUser() == null
                || usersFightModel.getOtherUser() == null) {
            return null;
        }
        UsersFight usersFight = this.usersFightsRepository.getNotFinishedFight(
                usersFightModel.getCurrentUser().getId(),
                usersFightModel.getOtherUser().getId());

        EditUsersFightModel editUsersFightModel = null;

        if (usersFight != null) {
            editUsersFightModel = this.modelMapper.map(usersFight, EditUsersFightModel.class);
        }
        return editUsersFightModel;
    }

    @Override
    public void update(EditUsersFightModel editUsersFightModel) {
        UsersFight usersFight = this.usersFightsRepository.findOne(editUsersFightModel.getId());
        usersFight.setIsChallengedAnsweredCorrect(editUsersFightModel.getIsChallengedAnsweredCorrect());
        usersFight.setIsChallengedUserAnswered(editUsersFightModel.getIsChallengedUserAnswered());
        usersFight.setIsChallengerAnsweredCorrect(editUsersFightModel.getIsChallengerAnsweredCorrect());;
        usersFight.setIsChallengerUserAnswered(editUsersFightModel.getIsChallengerUserAnswered());
        this.usersFightsRepository.saveAndFlush(usersFight);
    }

    @Override
    public int getChallengedChallengesCount(Long userId) {
        return this.usersFightsRepository.getChallengedChallengesCount(userId);
    }

    @Override
    public int getChallengerChallengesCount(Long userId) {
        return this.usersFightsRepository.getChallengerChallengesCount(userId);
    }

    @Override
    public List<UsersFightViewModel> getChallengerFightsByUsername(String username) {
        List<UsersFight> usersFights = this.usersFightsRepository.findByChallengerUsernameOrderByIsChallengerUserAnsweredAsc(username);
        List<UsersFightViewModel> usersFightViewModels = mapToUsersFightModel(usersFights,username);
        return usersFightViewModels;
    }

    @Override
    public List<UsersFightViewModel> getChallengedFightsByUsername(String username) {
        List<UsersFight> usersFights = this.usersFightsRepository.findByChallengedUsernameOrderByIsChallengedUserAnsweredAsc(username);
        List<UsersFightViewModel> usersFightViewModels = mapToUsersFightModel(usersFights,username);
        return usersFightViewModels;
    }


    private List<UsersFightViewModel> mapToUsersFightModel(List<UsersFight> usersFights,String username) {
        List<UsersFightViewModel> usersFightViewModels = new ArrayList<>();
        for (UsersFight usersFight : usersFights) {
            UsersFightViewModel usersFightViewModel = new UsersFightViewModel();
            UserViewModel otherUser = null;
            if (usersFight.getChallenger().getUsername().equals(username)){
                otherUser = this.modelMapper.map(usersFight.getChallenged(),UserViewModel.class);
            } else {
                 otherUser = this.modelMapper.map(usersFight.getChallenger(),UserViewModel.class);
            }

            usersFightViewModel.setOtherUser(otherUser);

            String result;
            if (usersFight.getIsChallengedAnsweredCorrect() == null || usersFight.getIsChallengerAnsweredCorrect() == null){
                result = null;
            } else if (usersFight.getIsChallengerAnsweredCorrect() && !usersFight.getIsChallengedAnsweredCorrect()){
                result = "Win";
            } else if(!usersFight.getIsChallengerAnsweredCorrect() && usersFight.getIsChallengedAnsweredCorrect()){
                result = "Loose";
            } else {
                result = "Draw";
            }

            usersFightViewModel.setFightResult(result);
            usersFightViewModels.add(usersFightViewModel);
        }
        return usersFightViewModels;
    }


}
