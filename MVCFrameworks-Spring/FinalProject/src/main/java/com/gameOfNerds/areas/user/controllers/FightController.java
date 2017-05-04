package com.gameOfNerds.areas.user.controllers;

import com.gameOfNerds.areas.connections.EmailSender;
import com.gameOfNerds.areas.question.models.bindingModels.QuestionModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserInfoModel;
import com.gameOfNerds.areas.user.models.bindingModels.UserModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.AddUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.EditUsersFightModel;
import com.gameOfNerds.areas.user.models.bindingModels.fights.UsersFightModel;
import com.gameOfNerds.areas.user.models.viewModels.UsersFightViewModel;
import com.gameOfNerds.areas.user.services.UserGameInfoService;
import com.gameOfNerds.areas.user.services.UserService;
import com.gameOfNerds.areas.user.services.UsersFightService;
import com.gameOfNerds.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.List;

@Controller
public class FightController {
    private final UsersFightService userFightService;
    private final UserService userService;
    private final UserGameInfoService userGameInfoService;

    @Autowired
    public FightController(UsersFightService userFightService, UserService userService, UserGameInfoService userGameInfoService) {
        this.userFightService = userFightService;
        this.userService = userService;
        this.userGameInfoService = userGameInfoService;
    }

    @GetMapping("/users/fight/{otherUser}")
    public String getUsersFightPage(@PathVariable Long otherUser, Model model,Principal principal){
        UsersFightModel usersFightModel = createUserFightModel(otherUser, principal);

        if (usersFightModel.getOtherUser() == null){
            model.addAttribute(Constants.VIEW, Constants.WRONG_PAGE_VIEW);
            return Constants.LAYOUT;
        } else if ( usersFightModel.getCurrentUser().getId().equals(usersFightModel.getOtherUser().getId())){
            model.addAttribute(Constants.VIEW, Constants.CAN_NOT_CHALLENGE_YOURSELF_VIEW);
            return Constants.LAYOUT;
        } else if (this.userFightService.isUsersInFight(usersFightModel)){
            model.addAttribute(Constants.VIEW, Constants.IN_FIGHT_VIEW);
            return Constants.LAYOUT;
        }
        model.addAttribute(Constants.VIEW, Constants.SINGLE_QUESTION_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/users/challenges/{otherUser}")
    public String getUsersChallengePage(@PathVariable Long otherUser, Model model,Principal principal){
        UsersFightModel usersFightModel = createUserFightModel(otherUser, principal);
        if (!this.userFightService.isUsersInFight(usersFightModel)){
            model.addAttribute(Constants.VIEW, Constants.NOT_IN_FIGHT_VIEW);
            return Constants.LAYOUT;
        }

        if (this.userFightService.isCurrentUserAnswerInCurrentFight(usersFightModel)){
            model.addAttribute(Constants.VIEW, Constants.ALREADY_ANSWERED_VIEW);
            return Constants.LAYOUT;
        }
        model.addAttribute(Constants.VIEW, Constants.SINGLE_QUESTION_VIEW);
        return Constants.LAYOUT;
    }


    @PutMapping("/fight/save/{challengedUserId}")
    public ResponseEntity<EditUsersFightModel> saveFight(@PathVariable Long challengedUserId, @RequestBody QuestionModel questionModel, Principal principal){
        AddUsersFightModel usersFightModel = new AddUsersFightModel();
        UserModel challenged = this.userService.findById(challengedUserId);
        UserModel challenger = this.userService.findByUsername(principal.getName());
        usersFightModel.setChallenged(challenged);
        usersFightModel.setChallenger(challenger);
        usersFightModel.setQuestion(questionModel);
        usersFightModel.setIsChallengedUserAnswered(false);
        usersFightModel.setIsChallengerUserAnswered(false);

        EditUsersFightModel editUsersFightModel = this.userFightService.save(usersFightModel);
//        this.sendEmailToChallengedUser(challenger,challenged);
        return new ResponseEntity<EditUsersFightModel>(editUsersFightModel,HttpStatus.OK);
    }

    @PutMapping("/fight/update")
    public ResponseEntity updateFight(@RequestBody EditUsersFightModel editUsersFightModel){
        this.userFightService.update(editUsersFightModel);
        this.givePoints(editUsersFightModel);
        return new ResponseEntity(HttpStatus.OK);
    }



    @GetMapping("/fight/progress/{otherUser}")
    public ResponseEntity<EditUsersFightModel> getCurrentFight(@PathVariable Long otherUser,Principal principal){
        UsersFightModel userModel = this.createUserFightModel(otherUser,principal);
        EditUsersFightModel editUsersFightModel = this.userFightService.getCurrentFight(userModel);
        return new ResponseEntity<EditUsersFightModel>(editUsersFightModel,HttpStatus.OK);
    }


    @GetMapping("/users/challenges")
    public String getChallengesPage(Model model,Principal principal){
        List<UsersFightViewModel> usersFights =this.userFightService.getChallengedFightsByUsername(principal.getName());
        model.addAttribute("fights",usersFights);
        model.addAttribute(Constants.VIEW,Constants.CHALLENGES_TABLE_VIEW);
        return Constants.LAYOUT;
    }

    @GetMapping("/users/challenger")
    public String getChallengerPage(Model model,Principal principal){
        List<UsersFightViewModel> usersFights =this.userFightService.getChallengerFightsByUsername(principal.getName());
        model.addAttribute("fights",usersFights);
        model.addAttribute(Constants.VIEW,Constants.CHALLENGES_TABLE_VIEW);
        return Constants.LAYOUT;
    }

    private UsersFightModel createUserFightModel(@PathVariable Long id, Principal principal) {
        UsersFightModel usersFightModel = new UsersFightModel();
        UserModel challenged = this.userService.findById(id);
        UserModel challenger = this.userService.findByUsername(principal.getName());
        usersFightModel.setOtherUser(challenged);
        usersFightModel.setCurrentUser(challenger);
        return usersFightModel;
    }

    private void sendEmailToChallengedUser(UserModel challenger, UserModel challenged){
        UserInfoModel challengerModel = this.userService.getUserInfoById(challenger.getId());
        UserInfoModel challengedModel = this.userService.getUserInfoById(challenged.getId());


        String subject = String.format(Constants.EMAIL_SUBJECT_CHALLENGE,challengerModel.getUsername());
        String text = String.format(Constants.EMAIL_TEXT_CHALLENGE,challengedModel.getUsername(),challengerModel.getUsername());

        EmailSender.sendEmail(challengedModel.getEmail(),subject,text);

    }

    private void givePoints(EditUsersFightModel editUsersFightModel) {
        if (editUsersFightModel.getIsChallengerUserAnswered() && editUsersFightModel.getIsChallengedUserAnswered()){
            if (editUsersFightModel.getIsChallengedAnsweredCorrect() && !editUsersFightModel.getIsChallengerAnsweredCorrect()){
                this.userGameInfoService.givePoints(editUsersFightModel.getChallenged().getId(),Constants.WINNER_POINTS);
            } else if (editUsersFightModel.getIsChallengerAnsweredCorrect() && !editUsersFightModel.getIsChallengedAnsweredCorrect()){
                this.userGameInfoService.givePoints(editUsersFightModel.getChallenger().getId(),Constants.WINNER_POINTS);
            } else {
                this.userGameInfoService.givePoints(editUsersFightModel.getChallenged().getId(),Constants.DRAW_POINTS);
                this.userGameInfoService.givePoints(editUsersFightModel.getChallenger().getId(),Constants.DRAW_POINTS);
            }
        }
    }

}
