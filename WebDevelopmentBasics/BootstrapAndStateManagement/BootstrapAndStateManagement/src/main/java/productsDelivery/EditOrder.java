package productsDelivery;

import productsDelivery.models.Product;
import productsDelivery.models.Status;
import productsDelivery.services.ProductService;
import productsDelivery.services.StatusService;
import productsDelivery.utils.WebUtils;

import java.util.Map;

public class EditOrder {
    private static Map<String, String> params;
    private static ProductService productService;
    private static StatusService statusService;

    public static void main(String[] args) {
        params = WebUtils.getParameters();
        productService = new ProductService();
        statusService = new StatusService();
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
                "        <div class=\"container-fluid\">\n" +
                "            <a href=\"productdelivery.cgi\"><h3>Back to orders</h3></a>\n" +
                "            <h2>Edit Order</h2>");

        Long productId = Long.parseLong(params.get("id"));
        String statusName = params.get("status");
        Product product = productService.getProductById(productId);
        if (statusName == null) {
            printEditProduct(product);
        } else {
            Status status = statusService.getStatusByName(statusName);
            productService.save(productId, status);
            printSuccessEdit(productId);
        }

    }

    private static void printSuccessEdit(Long id) {
        String format = "<h3>Successfully edited status of order with ID %d<h3>";
        System.out.printf(format,id);
    }

    private static void printEditProduct(Product product) {
        String format = "<form class=\"form-group\">\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Id: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <input class=\"form-control\" type=\"text\" name=\"id\" value=\"%s\" readonly/><br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Name: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <input class=\"form-control\" type=\"text\" name=\"name\" value=\"%s\" readonly/><br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Type: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <input class=\"form-control\" type=\"text\" name=\"type\" value=\"%s\" readonly/><br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Payment Date: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <input class=\"form-control\" type=\"datetime\" name=\"payment-date\" value=\"%s\" readonly/><br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Delivery Date: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <input class=\"form-control\" type=\"datetime\" name=\"delivery-date\" value=\"%s\" readonly/><br/>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <label class=\"col-sm-1\" >Delivery Date: </label>\n" +
                "                    <div class=\"form-group col-sm-3\">\n" +
                "                        <select class=\"form-control\" name=\"status\">\n" +
                "                            <option>Pending</option>\n" +
                "                            <option>Delivered</option>\n" +
                "                            <option>Declined</option>\n" +
                "                            <option>In Call to Confirm</option>\n" +
                "                        </select>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "                <div class=\"row\">\n" +
                "                    <div class=\"col-sm-3 col-sm-offset-1\">\n" +
                "                    <input type=\"submit\" class=\"btn btn-primary\" value=\"Edit\">\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </form>\n" +
                "        </div>";

        System.out.printf(format,
                product.getId(),
                product.getName(),
                product.getProductType(),
                product.getPaymentDate().toString(),
                product.getDeliveryDate().toString());
    }


    private static void printHTMLFooter() {
        String htmlFooter = "<script src=\"/jQuery/jquery.min.js\"></script>\n" +
                "        <script src=\"/bootstrap/js/bootstrap.min.js\"></script>\n" +
                "    </body>\n" +
                "</html>";
        System.out.println(htmlFooter);
    }
}
