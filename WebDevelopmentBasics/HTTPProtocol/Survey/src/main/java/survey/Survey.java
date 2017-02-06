package survey;

import java.io.*;
import java.util.Map;

public class Survey {
    public static void main(String[] args) {
        printHeader();
        printHtml();
        saveSurvey();
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html\n");
    }

    private static void printHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\survey\\html\\survey.html");

        System.out.println(html);
    }

    private static void saveSurvey() {
        Map<String, String> params = WebUtils.getParameters();


        File file = new File("D:\\Apache24\\cgi-bin\\survey\\survey-results.csv");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            for (Map.Entry<String, String> info : params.entrySet()) {
                bw.write(String.format("%s=%s,", info.getKey(), info.getValue()));
            }

            bw.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
