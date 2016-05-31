package org.piyush.models;

public class PackageItem {
	private long id;
	private String title;
	private long packageId;
	private long itemId;
	private String description;
	private int quantity;
	private double price;
	
	public PackageItem() {
		
	}

	public long getPackageId() {
		return packageId;
	}


	public void setPackageId(long packageId) {
		this.packageId = packageId;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getItemId() {
		return itemId;
	}

	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void increaseQuantity() {
		this.quantity += 1;
	}
	
	public void decreaseQuantity() {
		this.quantity -= 1;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "PackageItem [id=" + id + ", title=" + title + ", description=" + description + ", quantity=" + quantity
				+ ", price=" + price + "]";
	}
	
}
