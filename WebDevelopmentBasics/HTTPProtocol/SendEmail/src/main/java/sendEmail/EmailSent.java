package sendEmail;

import java.util.Map;

public class EmailSent {

    private static final int SUBJECT_LENGTH = 100;

    private static Map<String, String> params;

    public static void main(String[] args) {
        params = WebUtils.getParameters();
        printHeader();
        printHtml();
        printEmailInfo();
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html");
        if (hasError()) {
            System.out.println("Location: sendemail.cgi?sendingerror=true");
        }
        System.out.println();
    }

    private static void printHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\sendEmail\\html\\email-sent.html");
        System.out.println(html);
    }

    private static boolean hasError() {
        String subject = params.get("subject");
        return subject.length() > SUBJECT_LENGTH;
    }

    private static void printEmailInfo() {
        String to = params.get("email");
        String subject = params.get("subject");
        String message = params.get("message");

        System.out.printf("<p> To: %s </p><br/>", to);
        System.out.printf("<p> Subject: %s </p><br/>", subject);
        System.out.printf("<p> Message: %s </p><br/>", message);
    }
}
