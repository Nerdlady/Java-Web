package com.gameOfNerds.constants;

public class Constants {
    public static final String EMAIL_SUBJECT_CHALLENGE = "Challenged from %s";
    public static final String EMAIL_TEXT_CHALLENGE = "Hello %s!\n You are challenged by %s. Come and show you're the better one.\n May the NERD be in you.";
    public static final Double WINNER_POINTS = 5D;
    public static final Double DRAW_POINTS = 2.5D;

    //Error messages
    public static final String CLAN_NAME_LENGTH = "Clan name should be at least 4 symbols long.";
    public static final String QUESTION_CONTEXT_LENGTH = "Question context should be at least 10 symbols.";
    public static final String ANSWER_NOT_EMPTY = "Answer should not be empty.";
    public static final String USERNAME_LENGTH = "Username should be between 4 and 20 symbols";
    public static final String PASSWORD_LENGTH = "Password should be between 4 and 30 symbols";
    public static final String FULL_NAME_LENGTH = "Name should be at least 3 symbols.";
    public static final String ENTER_VALID_EMAIL = "Name should be at least 3 symbols.";

    // Views
    public static final String LAYOUT = "base-layout";
    public static final String VIEW = "view";
    public static final String CLANS_TABLE_VIEW = "/views/clan/clans-table";
    public static final String WRONG_PAGE_VIEW = "/views/wrong-page";
    public static final String CLAN_VIEW = "/views/clan/clan";
    public static final String NO_CLAN_VIEW = "/views/clan/no-clan";
    public static final String CREATE_CLAN_VIEW = "/views/clan/create-clan";
    public static final String QUESTION_TABLE_VIEW = "/views/question/questions-table";
    public static final String ADD_QUESTION_VIEW = "/views/question/add-question";
    public static final String SINGLE_QUESTION_VIEW = "/views/question/question";
    public static final String CAN_NOT_CHALLENGE_YOURSELF_VIEW = "/views/user/fight/challenge-yourself";
    public static final String IN_FIGHT_VIEW = "/views/user/fight/in-fight";
    public static final String NOT_IN_FIGHT_VIEW = "/views/user/fight/not-in-fight";
    public static final String ALREADY_ANSWERED_VIEW = "/views/user/fight/already-answered";
    public static final String CHALLENGES_TABLE_VIEW = "/views/user/challenges-table";
    public static final String REGISTER_VIEW = "/views/user/register-user";
    public static final String LOGIN_VIEW = "/views/user/login-user";
    public static final String SETTINGS_VIEW = "/views/user/update-user";
    public static final String ALL_USERS_VIEW = "/views/user/all-users";
    public static final String CHALLENGE_VIEW = "/views/user/challenge";
    public static final String PROFILE_VIEW = "/views/user/profile";
    public static final String HOME_VIEW = "/views/home";


}
