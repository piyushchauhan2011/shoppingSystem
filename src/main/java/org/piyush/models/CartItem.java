package org.piyush.models;

public class CartItem {
	private long id;
	private int quantity;
	
	private Product product;
	
	public CartItem() {
		
	}
	
	public CartItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	public boolean checkAvailability() {
		long totalQty = 0;
		for (Warehouse warehouse : this.product.getWarehouses()) {
			totalQty += warehouse.getQuantity();
		}
		if (totalQty < this.quantity)
			return false;
		else
			return true;
	}

	public void increaseQuantity() {
		this.quantity += 1;
	}
	
	public void decreaseQuantity() {
		this.quantity -= 1;
	}

	@Override
	public String toString() {
		return "CartItem [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
	}

}
