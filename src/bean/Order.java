package bean;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Order {
    LinkedHashMap<Product, Integer> productvsQty = new LinkedHashMap<Product, Integer>();

    public Map<Product, Integer> getProductvsQty() {
        return productvsQty;
    }

    public void setProductvsQty(LinkedHashMap<Product, Integer> productvsQty) {
        this.productvsQty = productvsQty;
    }

    public List<Product> getProductList() {
        return this.productvsQty.keySet().stream().collect(Collectors.toList());
    }

    public Float calculateTotalAmountAfterDiscount() {
        Float totalAmount = 0.0f;
        for (Product product : this.getProductList()) {
            totalAmount = totalAmount + (productvsQty.get(product) * product.getDiscountedPrice());
        }

        return totalAmount;
    }

    public Float calculateSavings() {
        Float actualTotal = 0.0f;
        for (Product product : this.getProductList()) {
            actualTotal = actualTotal + (productvsQty.get(product) * product.getActualPrice());
        }

        return actualTotal - calculateTotalAmountAfterDiscount();
    }
}
