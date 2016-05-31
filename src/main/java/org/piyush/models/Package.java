package org.piyush.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Package {
	private long id;
	private long orderId;
	private String status;
	private String trackingNumber;
	private String deliveryAddress;
	private String warehouseAddress;

	private List<PackageItem> packageItems;
	
	public Package() {
		this.packageItems = new ArrayList<>();
	}

	public PackageItem isPresent(PackageItem pi) {
		for(PackageItem packageItem: this.packageItems) {
			if(packageItem.getTitle().equals(pi.getTitle())) {
				return packageItem;
			}
		}
		return null;
	}
	
	public void insertPackageItem(PackageItem packageItem) {
		PackageItem c = null;
		for(PackageItem tmp: this.packageItems) {
			if (tmp.getTitle().equals(packageItem.getTitle())) {
				c = tmp;
				break;
			}
		}
		
		if(c == null) this.packageItems.add(packageItem);
		else c = packageItem;
	}
	
	public List<PackageItem> getPackageItems() {
		return packageItems;
	}

	public void setPackageItems(List<PackageItem> packageItems) {
		this.packageItems = packageItems;
	}
	
	public String getWarehouseAddress() {
		return warehouseAddress;
	}

	public void setWarehouseAddress(String warehouseAddress) {
		this.warehouseAddress = warehouseAddress;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrackingNumber() {
		return trackingNumber;
	}
	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}
	public String getDeliveryAddress() {
		return deliveryAddress;
	}
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	public String getRandomHexString(int numchars){
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        while(sb.length() < numchars){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, numchars);
    }
	
	public PackageItem findPackageItemByTitle(String title) {
		PackageItem pi = null;
		for(PackageItem packageItem: this.packageItems) {
			if(packageItem.getTitle().equals(title)) {
				pi = packageItem;
				break;
			}
		}
		return pi;
	}
	
	public PackageItem findPackageItemById(long id) {
		PackageItem pi = null;
		for(PackageItem packageItem: this.packageItems) {
			if(packageItem.getId() == id) {
				pi = packageItem;
				break;
			}
		}
		return pi;
	}

	@Override
	public String toString() {
		return "Package [id=" + id + ", orderId=" + orderId + ", status=" + status + ", trackingNumber="
				+ trackingNumber + ", deliveryAddress=" + deliveryAddress + ", warehouseAddress=" + warehouseAddress
				+ ", packageItems=" + packageItems + "]";
	}
	
}
