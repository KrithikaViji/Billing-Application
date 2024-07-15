package bean;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ProductsByCategory {
    static HashMap<String, HashSet<String>> productsByCategory = new HashMap<>();

    public static HashMap<String, HashSet<String>> getProductsByCategory() {
        return productsByCategory;
    }

    static {
        List<String> productFilesPath = List.of("src/files/books.txt", "src/files/clothes.txt",
                "src/files/food.txt");
        for (String filePath : productFilesPath) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String category = reader.readLine();
                HashSet<String> productList = new HashSet<>();
                productsByCategory.put(category, productList);
                String product;
                while ((product = reader.readLine()) != null) {
                    productList.add(product);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
