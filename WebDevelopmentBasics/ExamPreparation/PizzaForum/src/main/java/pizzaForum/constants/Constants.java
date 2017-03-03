package pizzaForum.constants;

public class Constants {
    public static final String BASE_LAYOUT = "base-layout";
    public static final String EMAIL_NOT_NULL = "Please enter your email address.";
    public static final String INVALID_EMAIL = "Please enter a valid email address.";
    public static final String EMAIL_ALREADY_REGISTER = "This email already registered";
    public static final String NAME_NOT_NULL = "Please enter your username.";
    public static final String INVALID_NAME = "Username should contain only lowercase letters and numbers.";
    public static final String PASSWORD_NOT_NULL = "Please enter your password.";
    public static final Integer NAME_MIN_LENGTH = 3;
    public static final Integer PASSWORD_LENGTH = 4;
    public static final String INVALID_NAME_LENGTH = String.format("Name must be at least %d symbols long .", NAME_MIN_LENGTH);
    public static final String INVALID_PASSWORD_LENGTH = String.format("Password must be exactly %d symbols long .", PASSWORD_LENGTH);
    public static final String CONFIRM_PASSWORD_NOT_NULL = "Please confirm your password.";
    public static final String PASSWORDS_MISMATCH = "Passwords do not match";
    public static final String WRONG_USERNAME_OR_PASSWORD = "Wrong username/email or password.";
    public static final String EMAIL_USERNAME_NOT_NULL = "Please enter your email address or username.";

}
