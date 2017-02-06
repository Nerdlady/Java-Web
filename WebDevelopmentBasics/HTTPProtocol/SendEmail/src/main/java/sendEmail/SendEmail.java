package sendEmail;

import java.io.*;
import java.util.Map;

public class SendEmail {
    private static final String USERNAME = "suAdmin";
    private static final String PASSWORD = "aDmInPw17";

    private static Map<String, String> params;

    public static void main(String[] args) {
        params = WebUtils.getParameters();
        printHeader();
        printHtml();
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html");
        if (hasLoginError() && !isLogin()) {
            System.out.println("Location: login.cgi?error=true");
        }
        System.out.println();
    }

    private static void printHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\sendEmail\\html\\send-email.html");
        if (hasSendingError()) {
            html = html + "<p style=\"color: red\">Subject must be no longer than 100 symbols!</p>";
        }
        System.out.println(html);
    }

    private static boolean hasSendingError() {

        String error = params.get("sendingerror");
        return "true".equals(error);
    }

    private static boolean hasLoginError() {
        String username = params.get("username");
        String password = params.get("password");

        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
            login(true);
            return false;
        }
        return true;
    }

    private static void login(boolean isLogin) {
        File file = new File("D:\\Apache24\\cgi-bin\\sendEmail\\islogin.txt");

        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            bw.write(String.valueOf(isLogin));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static boolean isLogin() {
        File file = new File("D:\\Apache24\\cgi-bin\\sendEmail\\islogin.txt");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return false;
                }
                return "true".equals(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
