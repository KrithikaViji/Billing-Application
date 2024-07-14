
import java.util.List;
import bean.Product;
import constants.Category;

public class DiscountCalculator {

    public void applyDiscountToProducts(List<Product> productList) {
        for (Product product : productList) {
            product.setDiscountedPrice(
                    calculateDiscount(product.getActualPrice(), product.getCategory(), product.isClearance()));
        }
    }

    private Float calculateDiscount(Float actualPrice, Category category, boolean isClearance) {

        Float discounted_price = actualPrice - (actualPrice * category.getDiscount_percent() / 100);
        if (isClearance) {
            discounted_price = discounted_price - (discounted_price * Category.CLEARANCE.getDiscount_percent() / 100);
        }

        return Float.parseFloat(String.format("%.2f", discounted_price));
    }

}
