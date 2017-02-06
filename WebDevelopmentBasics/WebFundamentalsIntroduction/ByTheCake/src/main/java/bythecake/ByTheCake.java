package bythecake;

public class ByTheCake {
    public static void main(String[] args) {
        setHeader();
        setHtml();
    }

    public static void setHeader() {
        System.out.println("Content-type: text/html \n");
    }

    public static void setHtml() {
        String html = WebUtils.readWholeFile("D:\\Apache24\\cgi-bin\\bythecake\\html\\by-the-cake.html");
        System.out.println(html);

    }
}

