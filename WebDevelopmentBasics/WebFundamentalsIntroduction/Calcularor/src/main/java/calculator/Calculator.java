package calculator;

import java.math.BigDecimal;
import java.util.Map;

public class Calculator {
    public static void main(String[] args) {
        setHeader();
        setHtml();
        readParameters();
    }

    private static void setHeader() {
        String header = "Content-type: text/html\n";
        System.out.println(header);
    }

    private static void setHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\calculator\\html\\calculator.html");
        System.out.println(html);
    }

    private static void readParameters() {
        Map<String, String> params = WebUtils.getParameters();
        BigDecimal numOne = new BigDecimal(params.get("num_one"));
        BigDecimal numTwo = new BigDecimal(params.get("num_two"));
        String sign = params.get("sign");

        String result = getResult(sign,numOne,numTwo);

        System.out.println("<p>" + result + "</p>");


    }

    private static String getResult(String sign, BigDecimal numOne, BigDecimal numTwo) {
        switch (sign) {
            case "+":
                return "Result: " + numOne.add(numTwo).toString();
            case "-":
                return "Result: " + numOne.subtract(numTwo).toString();
            case "*":
                return "Result: " + numOne.multiply(numTwo).toString();
            case "/":
                return "Result: " + numOne.divide(numTwo).toString();
            default:
                return "Invalid Sign";

        }
    }
}
