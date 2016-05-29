package org.piyush.dao;

import java.util.List;
import org.piyush.models.Cart;

public interface CartDao {
	public List<Cart> getAllCarts();
	public void addCart(Cart cart);
	public Cart getCartById(long cartId);
	public void deleteCart(long cartId);	
}