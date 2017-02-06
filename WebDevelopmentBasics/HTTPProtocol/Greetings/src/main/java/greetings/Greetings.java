package greetings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Greetings {
    public static void main(String[] args) {
        printHeader();

        writeData();
        int size = getDataSize();

        if (size == 0) {
            printHtmlFirstName();
        } else if (size == 1) {
            printHtmlLastName();
        } else if (size == 2) {
            printHtmlAge();
        } else {
            printHtmlGreetings();
        }
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html\n");
    }

    private static void printHtmlFirstName() {
        String html = WebUtils.readWholeFile("D:\\Lichni\\Dimi\\Softuni\\JavaWebDevelopment\\Basic\\Exercises\\HTTPProtocol\\Greetings\\src\\main\\resources\\html\\greetings-first-name.html");

        System.out.println(html);
    }

    private static void printHtmlLastName() {
        String html = WebUtils.readWholeFile("D:\\Lichni\\Dimi\\Softuni\\JavaWebDevelopment\\Basic\\Exercises\\HTTPProtocol\\Greetings\\src\\main\\resources\\html\\greetings-last-name.html");

        System.out.println(html);
    }

    private static void printHtmlAge() {
        String html = WebUtils.readWholeFile("D:\\Lichni\\Dimi\\Softuni\\JavaWebDevelopment\\Basic\\Exercises\\HTTPProtocol\\Greetings\\src\\main\\resources\\html\\greetings-age.html");

        System.out.println(html);
    }

    private static void printHtmlGreetings() {
        List<String> data = getData();
        String html = "<h1>Hello %s %s at age %s</h1>";
        System.out.printf(html, data.get(0), data.get(1), data.get(2));
    }

    private static void writeData() {
        File file = new File("D:\\Apache24\\cgi-bin\\greetings\\data.txt");

        Map<String, String> params = WebUtils.getParameters();
        String data = params.get("info");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,true))) {
            if (data != null) {
                bw.write(String.valueOf(data));
                bw.write(System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static List<String> getData() {
        File file = new File("D:\\Apache24\\cgi-bin\\greetings\\data.txt");
        List<String> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));) {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }

                data.add(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    private static int getDataSize() {
        return getData().size();
    }

}
