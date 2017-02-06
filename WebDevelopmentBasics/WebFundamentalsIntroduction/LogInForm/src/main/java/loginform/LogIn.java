package loginform;

import java.util.Map;

public class LogIn {
    public static void main(String[] args) {
        setHeader();
        setHtml();
        readParameters();
    }

    private static void setHeader(){
        System.out.println("Content-type: text/html\n");
    }

    private static void setHtml(){
        String html = WebUtils.readWholeFile("D:\\Lichni\\Dimi\\Softuni\\JavaWebDevelopment\\Basic\\Exercises\\WebFundamentalsIntroduction\\LogInForm\\src\\main\\resources\\html\\log-in.html");
        System.out.println(html);
    }

    private static void readParameters(){
        Map<String,String> params = WebUtils.getParameters();
        String username = params.get("username");
        String password = params.get("password");

        System.out.printf("<p>Hi %s, your password is %s</p>",username,password);
    }
}
