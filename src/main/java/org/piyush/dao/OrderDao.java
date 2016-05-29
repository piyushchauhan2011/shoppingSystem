package org.piyush.dao;

import java.util.List;
import org.piyush.models.Order;

public interface OrderDao {
	public List<Order> getAllOrders();
	public void addOrder(Order o);
	public Order getOrderById(long orderId);
	public void deleteOrder(long orderId);	
}