package org.piyush.models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private long id;
	private List<CartItem> cartItems;

	public Cart() {
		cartItems = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CartItem cartItemPresent(long productId) {
		for(CartItem ci: this.cartItems) {
			if(ci.getProductId() == productId) return ci;
		}
		return null;
	}
	
	public List<CartItem> addProduct(long productId) {
		CartItem cartItem = this.cartItemPresent(productId);
		if(cartItem != null) {
			cartItem.increaseQuantity();
		} else {
			this.cartItems.add(new CartItem(productId, 1));
		}
		return this.cartItems;
	}
	
	public List<CartItem> getCartItems() {
		return this.cartItems;
	}
	
	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartItems=" + cartItems + "]";
	}
}
