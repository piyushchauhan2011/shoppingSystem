package org.piyush.models;

import java.util.ArrayList;
import java.util.List;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Digits;

public class Product {
	
	private long id;
	
	private String title;
	
	private String description;
	
	private String imageUrl;
	
	private double price;
	
	private List<Warehouse> warehouses;
	
	public Product() {
		this.warehouses = new ArrayList<>();
	}

	public Product(String title, String description, String imageUrl, double price) {
		this.title = title;
		this.description = description;
		this.imageUrl = imageUrl;
		this.price = price;
		this.warehouses = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Warehouse> getWarehouses() {
		return warehouses;
	}

	public void setWarehouses(List<Warehouse> warehouses) {
		this.warehouses = warehouses;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", imageUrl="
				+ imageUrl + ", price=" + price + "]";
	}
}
