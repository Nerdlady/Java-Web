package pizzaMore.pages;

import pizzaMore.models.Cookie;
import pizzaMore.models.Header;
import pizzaMore.models.entities.Session;
import pizzaMore.models.entities.SessionData;
import pizzaMore.services.SessionService;
import pizzaMore.utils.WebUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Home {
    private static Map<String, String> params;
    private static Map<String, Cookie> cookies;
    private static Header header;
    private static SessionService sessionService;

    static {
        params = new HashMap<>();
        cookies = new HashMap<>();
        header = new Header();
        sessionService = new SessionService();
    }

    public static void main(String[] args) {
        readParameters();
        header.printHeader();
        readCookies(args);
        readHtml();
    }

    public static void readParameters() {
        params = WebUtils.getParameters();
        for (String param : params.keySet()) {
            switch (param) {
                case "language":
                    String value = params.get("language");
                    setCookie("lang", value);
                    break;
                case "singin":
                    goToSignIn();
                    break;
                case "signout":
                    goToSignIn();
                    break;
                case "signup":
                    goToSignUp();
            }
        }
    }

    private static void goToSignUp() {
        header.addLocation("signup.cgi");
    }

    private static void goToSignIn() {
        header.addLocation("signin.cgi");
    }

    private static void setCookie(String key, String value) {
        Cookie cookie = new Cookie(key, value);
        header.addCookie(cookie);
    }

    private static void readCookies(String[] args) {
        if (args.length == 0) {
            return;
        }

        for (String incomingCookie : args) {
            String[] tokens = incomingCookie.split("=");
            String key = tokens[0];
            String value = tokens[1];
            value.replace(";", "");

            Cookie cookie = new Cookie(key, value);
            cookies.put(key, cookie);
        }
    }

    private static void readHtml() {
        Cookie sessionCookie = cookies.get("sid");
        String username = null;
        if (sessionCookie != null) {
            Long sid = Long.parseLong(sessionCookie.getValue());
            Session session = sessionService.findById(sid);

            if (params.containsKey("signout")) {
                signOut(sid);
                session = null;
            }

            if (session != null) {
                Set<SessionData> sessionData = session.getSessionData();
                for (SessionData data : sessionData) {
                    if ("username".equals(data.getKey())){
                        username = data.getValue();
                    }
                }
            }
        }


        Cookie languageCookie = cookies.get("lang");
        if (!WebUtils.isPost()) {
            if (languageCookie != null) {
                changeLanguage(languageCookie.getValue(),username);
            } else {
                readHtmlEn(username);
            }

        } else {
            if (params.containsKey("language")) {
                String language = params.get("language");
                changeLanguage(language,username);
            }
        }
    }

    private static void changeLanguage(String value,String username) {
        if ("DE".equals(value)) {
            readHtmlDe(username);
        } else {
            readHtmlEn(username);
        }
    }

    private static void signOut(Long id) {
        sessionService.delete(id);
    }

    private static void readHtmlEn(String username) {
        String signButtonValue = "Sign In";
        String signButtonName = "singin";

        if (username != null){
            signButtonName ="signout";
            signButtonValue = String.format("Sign Out(%s)",username);
        }
        String html = WebUtils.readWholeFile("D:\\Apache24\\htdocs\\html\\home.html");
        System.out.printf(html, "DE",signButtonName, signButtonValue, "Sign Up");
    }

    private static void readHtmlDe(String username) {
        String signButtonValue = "Anmelden";
        String signButtonName = "singin";

        if (username != null){
            signButtonName ="signout";
            signButtonValue = String.format("Ausloggen(%s)",username);
        }
        String html = WebUtils.readWholeFile("D:\\Apache24\\htdocs\\html\\home.html");
        System.out.printf(html, "EN",signButtonName, signButtonValue, "Unterschreiben");
    }
}
