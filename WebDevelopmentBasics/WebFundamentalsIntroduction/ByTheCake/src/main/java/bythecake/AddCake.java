package bythecake;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

public class AddCake {
    public static void main(String[] args) {
        setHeader();
        setHtml();
        readParameters();
    }

    private static void setHeader() {
        System.out.println("Content-type: text/html \n");
    }

    private static void setHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\bythecake\\html\\add-cake.html");
        System.out.println(html);

    }


    private static void readParameters() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        for (Map.Entry<String, String> stringStringEntry : parameterMap.entrySet()) {
            System.out.println("<bg/><p>" + stringStringEntry.getKey() + ": ");
            System.out.println(stringStringEntry.getValue() + "</p>");
        }

        String name = parameterMap.get("name");
        BigDecimal price = new BigDecimal(parameterMap.get("price"));
        Cake cake = new Cake(name, price);

        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter("D:\\Apache24\\cgi-bin\\bythecake\\database.csv", true));
        ) {
            bw.write(cake.getName() + "," + cake.getPrice() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
