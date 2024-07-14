package bean;

import java.util.HashSet;
import java.util.Map;

import constants.Category;

public class Product {

    String productName;
    Float actualPrice;
    Float discountedPrice;
    boolean isClearance;
    Category category;

    public Product(String productName, Float actualPrice, boolean isClearance) {
        this.productName = productName;
        this.actualPrice = actualPrice;
        this.isClearance = isClearance;
        setCategoryForProduct();
    }

    private void setCategoryForProduct() {
        for (Map.Entry<String, HashSet<String>> categoryvsProduct : ProductsByCategory.getProductsByCategory()
                .entrySet()) {
            if (categoryvsProduct.getValue().contains(this.productName)) {
                this.category = Category.valueOf(categoryvsProduct.getKey());
                break;
            }
        }
        if (this.category == null) {
            this.category = Category.OTHER_ITEMS;
        }
    }

    public String getProductName() {
        return productName;
    }

    public Float getActualPrice() {
        return actualPrice;
    }

    public Float getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(Float discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public boolean isClearance() {
        return isClearance;
    }

    public Category getCategory() {
        return category;
    }

}
