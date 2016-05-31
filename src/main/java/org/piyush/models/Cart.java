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
		CartItem c = null;
		for(CartItem tmp: this.cartItems) {
			if (tmp.getId() == cartItem.getId()) {
				c = tmp;
				break;
			}
		}
		
		if(c == null) this.cartItems.add(cartItem);
		else c = cartItem;
	}
	
	public CartItem findCartItemById(long cartItemId) {
		CartItem c = null;
		for(CartItem cartItem: this.cartItems) {
			if(cartItem.getId() == cartItemId) {
				c = cartItem;
				break;
			}
		}
		return c;
	}
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItem> cartItems) {
		this.cartItems = cartItems;
	}
	
	public boolean isValid() {
		boolean isValid = true;
		for(CartItem ci: this.cartItems) {
			isValid &= ci.checkAvailability();
		}
		return isValid;
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", cartItems=" + cartItems + "]";
	}
}
