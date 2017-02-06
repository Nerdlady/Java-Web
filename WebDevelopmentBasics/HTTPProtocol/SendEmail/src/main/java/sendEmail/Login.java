package sendEmail;

import java.io.*;
import java.util.Map;

public class Login {

    public static void main(String[] args) {
        printHeader();
        printHtml();
        login(false);
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html\n");
    }

    private static void printHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\sendEmail\\html\\login.html");
        if (hasError()){
            html =  html + "<p style=\"color: red\">Invalid username or password!</p>";
        }
        System.out.println(html);
    }

    private static boolean hasError() {
        Map<String,String> params = WebUtils.getParameters();
        String error = params.get("error");

        return "true".equals(error);

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


}
