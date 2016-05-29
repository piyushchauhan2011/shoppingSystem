package org.piyush.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.piyush.models.Cart;
import org.piyush.models.Order;
import org.piyush.dao.CartDao;

public class OrderDaoMemImpl implements OrderDao {

	private List<Order> orders;
	private final AtomicLong counter = new AtomicLong();
	private CartDao cdao = DaoFactory.getInstance().getCartDao();
	
	public OrderDaoMemImpl(){
		orders = new ArrayList<Order>();
		
		Cart cart = cdao.getCartById(1);
		Order o1 = new Order();
		o1.setCart(cart);
		o1.setStatus("shipped");
		addOrder(o1);
		
		cart = cdao.getCartById(2);
		Order o2 = new Order();
		o2.setCart(cart);
		o2.setStatus("received");
		addOrder(o2);
	}
	
	@Override
	public void addOrder(Order order) {
		order.setId(counter.incrementAndGet());
		orders.add(order);
	}

	@Override
	public void deleteOrder(long orderId) {
		for (int i = 0; i < orders.size(); i ++){
			if (orders.get(i).getId() == orderId){
				orders.remove(i);
				break;
			}
		}
	}

	@Override
	public List<Order> getAllOrders() {
		return orders;
	}

	@Override
	public Order getOrderById(long orderId) {
		for (Order order: orders){
			if (order.getId() == orderId)
				return order;
		}
		return null;
	}
}
