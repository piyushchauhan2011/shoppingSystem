package org.piyush.models;

public class Warehouse {
	private long id;
	private long productId;
	private String location;
	private long quantity;
	
	public Warehouse() {
		
	}
	
	public Warehouse(long productId, String location, long quantity) {
		super();
		this.productId = productId;
		this.location = location;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "Warehouse [id=" + id + ", productId=" + productId + ", location=" + location + ", quantity=" + quantity
				+ "]";
	}
}
