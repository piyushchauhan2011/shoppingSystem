package org.piyush.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.piyush.models.Cart;

public class CartDaoMemImpl implements CartDao {

	private List<Cart> carts;
	private final AtomicLong counter = new AtomicLong();
	
	public CartDaoMemImpl(){
		
		Cart c = new Cart();
		c.addProduct(1);
		c.addProduct(1);
		c.addProduct(1);
		c.addProduct(2);
		c.addProduct(2);
		
		carts = new ArrayList<Cart>();
		addCart(c);
	}
	
	@Override
	public void addCart(Cart cart) {
		cart.setId(counter.incrementAndGet());
		carts.add(cart);
	}

	@Override
	public void deleteCart(long cartId) {
		for (int i = 0; i < carts.size(); i ++){
			if (carts.get(i).getId() == cartId){
				carts.remove(i);
				break;
			}
		}
	}

	@Override
	public List<Cart> getAllCarts() {
		return carts;
	}

	@Override
	public Cart getCartById(long cartId) {
		for (Cart cart: carts){
			if (cart.getId() == cartId)
				return cart;
		}
		return null;
	}

}
