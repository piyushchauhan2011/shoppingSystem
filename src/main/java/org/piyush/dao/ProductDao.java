package org.piyush.dao;

import java.util.List;
import org.piyush.models.Product;

public interface ProductDao {
	public List<Product> getAllProducts();
	public void addProduct(Product p);
	public Product getProductById(long productId);
	public void updateProduct(Product p);
	public void deleteProduct(long productId);	
}