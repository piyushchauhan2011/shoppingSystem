package org.piyush.models;

public class Order {
	private long id;
	private Cart cart;
	private long userId;
	private String status;
	
	public Order() {
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", cart=" + cart + ", userId=" + userId + ", status=" + status + "]";
	}
	
	
}
