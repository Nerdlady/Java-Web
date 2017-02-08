package pizzaMore.pages;

import pizzaMore.models.Cookie;
import pizzaMore.models.Header;
import pizzaMore.models.entities.Session;
import pizzaMore.models.entities.User;
import pizzaMore.services.SessionService;
import pizzaMore.services.UserService;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;

public class SignIn {
    private static Map<String, String> params;
    private static Header header;
    private static SessionService sessionService;
    private static UserService userService;

    static {
        params = new HashMap<>();
        header = new Header();
        sessionService = new SessionService();
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
                case "signin":
                    signIn();
                    break;
                case "main":
                    header.addLocation("home.cgi");
            }
        }
    }

    private static void signIn() {
        String username = params.get("username");
        String password = params.get("password");

        User user = userService.findUserByUsernameAndPassword(username,password);
        if (user != null){
            Session session = new Session();
            session.addSessionData("username",username);
            Long sid = sessionService.create(session);
            Cookie sessionCookie = new Cookie("sid",String.valueOf(sid));
            header.addCookie(sessionCookie);
            Cookie  rememberMeCookie = new Cookie("rememberme","on");
            header.addCookie(rememberMeCookie);
            header.addLocation("home.cgi");
        }
    }

    private static void readHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\htdocs\\html\\signin.html");
        System.out.println(html);
    }
}
