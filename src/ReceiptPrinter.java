
import java.util.Map.Entry;

import bean.Order;
import bean.Product;

public class ReceiptPrinter {
    public void printReceipt(Order order) {
        for (Entry<Product, Integer> productvsQty : order.getProductvsQty().entrySet()) {
            Product product = productvsQty.getKey();
            System.out.println(productvsQty.getValue() + (product.isClearance() ? " clearance " : " ")
                    + product.getProductName() + " at "
                    + String.format("%.2f", order.getProductvsQty().get(product) * product.getDiscountedPrice()));
        }
        System.out.println("Total: " + String.format("%.2f", order.calculateTotalAmountAfterDiscount()));
        System.out.println("You saved: " + String.format("%.2f", order.calculateSavings()));
    }
}
