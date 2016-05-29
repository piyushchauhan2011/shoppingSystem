package org.piyush.models;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<Long, CartItem> carts;

	public Cart() {
		carts = new HashMap<Long, CartItem>();
	}

	public void addItem(Product p) {
		long productId = p.getProductId();
		CartItem ci = carts.get(productId);
		if (ci == null) {
			carts.put(productId, new CartItem(p, 1)); // add the new product
		} else { // increase the quantity
			ci.increaseQuantity();
		}
	}

	public Collection<CartItem> getItems() {
		return carts.values();
	}
}
