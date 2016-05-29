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
		Cart cart = cdao.getCartById(1);
		Order o = new Order();
		o.setCart(cart);
		o.setStatus("in-transit");
		
		orders = new ArrayList<Order>();
		addOrder(o);
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
