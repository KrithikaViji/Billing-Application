import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.Scanner;

import bean.Order;
import bean.Product;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        LinkedHashMap<Product, Integer> pdtVsQtyOrdered = new LinkedHashMap<Product, Integer>();
        try {
            int noOfItems = sc.nextInt();
            if (noOfItems <= 0) {
                System.out.println("Please provide the total purchased count as a positive value");
                sc.close();
                return;
            }
            sc.nextLine();
            for (int i = 0; i < noOfItems; i++) {
                String input = sc.nextLine();
                String[] pdtDetails;
                pdtDetails = getQtyPdtAndPrice(input);
                validateInput(pdtDetails);
                Product product = new Product(pdtDetails[1].replaceFirst("clearance ", ""),
                        Float.parseFloat(pdtDetails[2]),
                        pdtDetails[1].contains("clearance"));
                pdtVsQtyOrdered.put(product, Integer.parseInt(pdtDetails[0]));
            }
            sc.close();
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println("Please check the input provided");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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
            throw new Exception(
                    "Provide provide product details in the correct format. <Quantity> <clearance if it is clearance item> <product name> <price>");
        }

    }

    private static void validateInput(String[] pdtDetails) throws Exception {
        if (!pdtDetails[0].matches("\\d+") || !pdtDetails[2].matches("\\d+\\.?\\d+")) {
            throw new Exception("Provide check the quantity and price provided.");
        }
    }
}
