package services;
import models.Product;

import java.util.List;

public interface ProductService{
    String createProduct(String name, double price);
    Product updateProduct(Long id, String name, double price);
    Long deleteProduct(Long id);
    Product findProductById(Long id);
    List<Product> showAllProducts();
}
