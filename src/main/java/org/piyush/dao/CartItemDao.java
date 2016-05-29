package org.piyush.dao;

import java.util.List;
import org.piyush.models.CartItem;

public interface CartItemDao {
	public List<CartItem> getAllCartItems();
	public void addCartItem(CartItem cartItem);
	public CartItem getCartItemById(long cartItemId);
	public void updateCartItem(CartItem cartItem);
	public void deleteCartItem(long cartItemId);	
}