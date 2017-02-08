package pizzaMore.pages;

import pizzaMore.models.Header;
import pizzaMore.models.entities.User;
import pizzaMore.services.UserService;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class SignUp {
    private static Map<String, String> params;
    private static Header header;
    private static UserService userService;

    static {
        params = new HashMap<>();
        header = new Header();
        userService = new UserService();
    }

    public static void main(String[] args) {
        readParameters();
        header.printHeader();
        readHtml();
    }

    public static void readParameters() {
        params = WebUtils.getParameters();
        String username = null;
        String password = null;
        for (String param : params.keySet()) {
            switch (param) {
                case "username":
                   username = params.get("username");
                    break;
                case "password":
                    password = params.get("password");
                    break;
                case "signup":
                    if (username.isEmpty() || password.isEmpty()){
                        return;
                    }

                    User user = new User(username,password);
                    createUser(user);
                    header.addLocation("signin.cgi");
                    break;
                case "main":
                    header.addLocation("home.cgi");
            }
        }
    }

    private static void createUser(User user) {
        userService.createUser(user);
    }

    private static void readHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\htdocs\\html\\signup.html");
        System.out.println(html);
    }
}
