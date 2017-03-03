package softuni.constants;

public class Constants {
    public static final String TITLE = "title";
    public static final String EMAIL_NOT_NULL = "Please enter your email address.";
    public static final String INVALID_EMAIL = "Please enter a valid email address.";
    public static final String EMAIL_ALREADY_REGISTER = "This email already registered";
    public static final String NAME_NOT_NULL = "Please enter your name.";
    public static final String INVALID_NAME = "Name should only contains English alphabet characters.";
    public static final String PASSWORD_NOT_NULL = "Please enter your password.";
    public static final Integer NAME_MIN_LENGTH = 5;
    public static final Integer NAME_MAX_LENGTH = 50;
    public static final Integer PASSWORD_MIN_LENGTH = 3;
    public static final Integer PASSWORD_MAX_LENGTH = 30;
    public static final String INVALID_NAME_LENGTH = String.format("Name should be between %d and %d symbols.", NAME_MIN_LENGTH, NAME_MAX_LENGTH);
    public static final String INVALID_PASSWORD_LENGTH = String.format("Password should be between %d and %d symbols.", PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH);
    public static final String CONFIRM_PASSWORD_NOT_NULL = "Please confirm your password.";
    public static final String PASSWORDS_MISMATCH = "Passwords do not match";
    public static final String WRONG_EMAIL = "Email not registered";
    public static final String WRONG_PASSWORDS = "Wrong password";
    public static final String ERRORS = "errors";
    public static final String VIEW = "view";
    public static final String BLOG_TITLE = "Softuni Blog";
    public static final String BASE_LAYOUT = "base-layout";
    public static final String USER_SESSION = "user";
    public static final String ARTICLE_TITLE_NOT_NULL = "Please enter article title.";
    public static final Integer ARTICLE_TITLE_MIN_LENGTH = 30;
    public static final String INVALID_ARTICLE_TITLE_LENGTH = String.format("Article title should be at least %d symbols long.", ARTICLE_TITLE_MIN_LENGTH);
    public static final String ARTICLE_CONTENT_NOT_NULL = "Please enter article content.";
    public static final Integer ARTICLE_CONTENT_MIN_LENGTH = 50;
    public static final String INVALID_ARTICLE_CONTENT_LENGTH = String.format("Article content should be at least %d symbols long.", ARTICLE_TITLE_MIN_LENGTH);
}
