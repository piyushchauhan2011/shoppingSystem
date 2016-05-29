package org.piyush.models;

public class Order {
	private long id;
	private Cart cart;
	private User user;
	private String status;
	
	public Order() {
		
	}

	public Order(Cart cart, User user, String status) {
		super();
		this.cart = cart;
		this.user = user;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", cart=" + cart + ", user=" + user + ", status=" + status + "]";
	}
}
