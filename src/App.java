import java.util.LinkedHashMap;
import java.util.Scanner;

import bean.Order;
import bean.Product;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int noOfItems = sc.nextInt();
        if (noOfItems <= 0) {
            System.out.println(
                    "Input given doesn't match the expected format. Please provide the total purchased product count");
            sc.close();
            return;
        }
        sc.nextLine();
        LinkedHashMap<Product, Integer> pdtVsQtyOrdered = new LinkedHashMap<Product, Integer>();
        for (int i = 0; i < noOfItems; i++) {
            String input = sc.nextLine();
            String[] pdtDetails;
            try {
                pdtDetails = getQtyPdtAndPrice(input);
                validateInput(pdtDetails);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }
            Product product = new Product(pdtDetails[1].replaceFirst("clearance ", ""), Float.parseFloat(pdtDetails[2]),
                    pdtDetails[1].contains("clearance"));
            pdtVsQtyOrdered.put(product, Integer.parseInt(pdtDetails[0]));
        }
        sc.close();

        if (pdtVsQtyOrdered.isEmpty()) {
            return;
        }

        Order order = new Order();
        order.setProductvsQty(pdtVsQtyOrdered);

        DiscountCalculator discountCalculator = new DiscountCalculator();
        discountCalculator.applyDiscountToProducts(order.getProductList());

        ReceiptPrinter receiptPrinter = new ReceiptPrinter();
        receiptPrinter.printReceipt(order);

    }

    private static String[] getQtyPdtAndPrice(String input) throws Exception {
        try {
            String[] qtyAndProduct = input.split(" ", 2);
            String[] productAndPrice = qtyAndProduct[1].split(" at ", 2);
            return new String[] { qtyAndProduct[0], productAndPrice[0], productAndPrice[1] };
        } catch (Exception e) {
            throw new Exception("Provide check the input format provided.");
        }

    }

    private static void validateInput(String[] pdtDetails) throws Exception {
        if (!pdtDetails[0].matches("\\d+") || !pdtDetails[2].matches("\\d+\\.?\\d+")) {
            throw new Exception("Provide check the quantity and price provided.");
        }
    }
}
