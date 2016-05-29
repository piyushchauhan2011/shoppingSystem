package org.piyush.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Digits;

public class Product {
	
	@NotNull
	@Digits(fraction = 0, integer = 11)
	private long productId;
	
	@NotNull
	private String title;
	
	private String description;
	
	private String imageUrl;
	
	@NotNull
	@Digits(fraction = 6, integer = 11)
	private double price;

	public Product() {
		super();
	}

	public Product(long productId, String title, String description, String imageUrl, double price) {
		super();
		this.productId = productId;
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", title=" + title + ", description=" + description + ", imageUrl="
				+ imageUrl + ", price=" + price + "]";
	}
}
