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
	
	public CartItem isPresent(Product product) {
		for(CartItem cartItem: this.cartItems) {
			if(cartItem.getProduct().getId() == product.getId()) return cartItem;
		}
		return null;
	}
	
	public void insertCartItem(CartItem cartItem) {
		this.cartItems.add(cartItem);
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartItems=" + cartItems + "]";
	}
}
