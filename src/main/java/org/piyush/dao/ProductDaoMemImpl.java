package org.piyush.dao;

import java.util.ArrayList;
import java.util.List;

import org.piyush.models.Product;

public class ProductDaoMemImpl implements ProductDao {

	private List<Product> products;
	private int lastId = 0;
	
	public ProductDaoMemImpl(){
		products = new ArrayList<Product>();
		addProduct(new Product(1, "Web Application Architecture","textbook","webapplication.jpg",79.95));
		addProduct(new Product(2, "Internet How to Program","textbook","wwwprogramming.jpg",109.95));
	}
	@Override
	public void addProduct(Product p) {
		lastId ++;
		p.setProductId(lastId);
		products.add(p);
	}

	@Override
	public void deleteProduct(long productId) {
		for (int i = 0; i < products.size(); i ++){
			if (products.get(i).getProductId() == productId){
				products.remove(i);
				break;
			}
		}
	}

	@Override
	public List<Product> getAllProducts() {
		return products;
	}

	@Override
	public Product getProductById(long productId) {
		for (Product prod: products){
			if (prod.getProductId() == productId)
				return prod;
		}
		return null;
	}

	@Override
	public void updateProduct(Product p) {
		long prodId = p.getProductId();
		for (int i = 0; i < products.size(); i++){
			if (products.get(i).getProductId() == prodId){
				products.set(i,p );
				return;
			}
		}
	}

}
