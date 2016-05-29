package org.piyush.models;

public class CartItem {
	private long id;
	private long productId;
	private long cartId;
	private int quantity;
	
	public CartItem() {
		
	}
	
	public CartItem(long productId, int quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void increaseQuantity() {
		this.quantity += 1;
	}

}
