package bythecake;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowseCake {
    public static void main(String[] args) {
        setHeader();
        setHtml();
        readParameters();
    }
    public static void setHeader() {
        System.out.println("Content-type: text/html \n");
    }

    public static void setHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\bythecake\\html\\browse-cakes.html");
        System.out.println(html);
    }

    private static void readParameters() {
        Map<String, String> parameterMap = WebUtils.getParameters();
        String name = parameterMap.get("search");
        List<Cake> cakes = new ArrayList();
        try (
                BufferedReader br
                        = new BufferedReader(
                        new FileReader("D:\\Apache24\\cgi-bin\\bythecake\\database.csv"));
        ) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                String cakeName = tokens[0];
                BigDecimal cakePrice = new BigDecimal(tokens[1]);
                Cake cake = new Cake(cakeName, cakePrice);
                cakes.add(cake);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Cake cake : cakes) {
            String cakeName = cake.getName();
            BigDecimal cakePrice = cake.getPrice();
            if(cakeName.toLowerCase().contains(name.toLowerCase())){
                System.out.println("<p>Name: "+cakeName+" Price: " + cakePrice +"</p>");
            }
        }


    }
}