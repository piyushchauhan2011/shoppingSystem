package org.piyush.models;

public class Order {
	private long id;
	private long cartId;
	private long userId;
	private String status;
	
	public Order() {
		
	}

	public Order(long id, long cartId, long userId, String status) {
		super();
		this.id = id;
		this.cartId = cartId;
		this.userId = userId;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
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
		return "Order [id=" + id + ", cartId=" + cartId + ", userId=" + userId + ", status=" + status + "]";
	}
	
}
