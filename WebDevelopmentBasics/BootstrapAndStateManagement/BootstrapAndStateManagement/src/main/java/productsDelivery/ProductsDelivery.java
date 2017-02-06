package productsDelivery;

import productsDelivery.models.Product;
import productsDelivery.models.Status;
import productsDelivery.services.ProductService;

import java.util.List;

public class ProductsDelivery {
    public static void main(String[] args) {
        printHeader();
        printHTML();
    }

    private static void printHeader() {
        System.out.println("Content-type: text/html\n");
    }

    private static void printHTML() {
        printHTMLHead();
        printHTMLBody();
        printHTMLFooter();
    }

    private static void printHTMLHead() {
        String htmlHead = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "    <head>\n" +
                "        <meta charset=\"UTF-8\"/>\n" +
                "        <title>Products Orders</title>\n" +
                "        <link rel=\"stylesheet\" href=\"/bootstrap/css/bootstrap.min.css\"/>\n" +
                "    </head>";
        System.out.println(htmlHead);
    }

    private static void printHTMLBody() {
        System.out.println("<body>\n" +
                "        <h3>Orders</h3>\n" +
                "        <div class=\"container-fluid\">\n" +
                "            <table class=\"table table-responsive table-condensed table-hover table-striped\">\n" +
                "                <tr>\n" +
                "                    <td><strong>ID</strong></td>\n" +
                "                    <td><strong>Name</strong></td>\n" +
                "                    <td><strong>Payment Date</strong></td>\n" +
                "                    <td><strong>Delivery Date</strong></td>\n" +
                "                    <td><strong>Order Status</strong></td>\n" +
                "                    <td><strong>Modify</strong></td>\n" +
                "                </tr>");
        ProductService productService = new ProductService();
        List<Product> products = productService.getProducts();

        for (Product product : products) {
            printProduct(product);
        }

    }

    private static void printProduct(Product product) {
        String format = "<tr class=\"%s\">\n" +
                "                    <td>%d</td>\n" +
                "                    <td><strong>%s</strong> (%s)</td>\n" +
                "                    <td>%s</td>\n" +
                "                    <td>%s</td>\n" +
                "                    <td>%s</td>\n" +
                "                    <td><a href=\"editorder.cgi?id=%d\">Edit</a></td>\n" +
                "                </tr>";

        String backgroundColor = formatBackgroundColor(product.getStatus());

        System.out.printf(format,
                backgroundColor,
                product.getId(),
                product.getName(),
                product.getProductType(),
                product.getPaymentDate().toString(),
                product.getDeliveryDate().toString(),
                product.getStatus().getName(),
                product.getId());
    }

    private static String formatBackgroundColor(Status status) {
        switch (status.getName()) {
            case "Pending":
                return "active";
            case "Delivered":
                return "success";
            case "Declined":
                return "danger";
            case "In Call to Confirm":
                return "warning";
            default:
                return "";
        }
    }

    private static void printHTMLFooter() {
        String htmlFooter = "<script src=\"/jQuery/jquery.min.js\"></script>\n" +
                "        <script src=\"/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "    </body>\n" +
                "</html>";
        System.out.println(htmlFooter);
    }


}
