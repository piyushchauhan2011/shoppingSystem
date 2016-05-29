package org.piyush.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.piyush.models.CartItem;

public class CartItemDaoMemImpl implements CartItemDao {

	private List<CartItem> cartItems;
	private final AtomicLong counter = new AtomicLong();
	
	public CartItemDaoMemImpl(){
		cartItems = new ArrayList<CartItem>();
		addCartItem(new CartItem(1, 2));
		addCartItem(new CartItem(2, 4));
	}
	@Override
	public void addCartItem(CartItem cartItem) {
		cartItem.setId(counter.incrementAndGet());
		cartItems.add(cartItem);
	}

	@Override
	public void deleteCartItem(long CartItemId) {
		for (int i = 0; i < cartItems.size(); i ++){
			if (cartItems.get(i).getId() == CartItemId){
				cartItems.remove(i);
				break;
			}
		}
	}

	@Override
	public List<CartItem> getAllCartItems() {
		return cartItems;
	}

	@Override
	public CartItem getCartItemById(long CartItemId) {
		for (CartItem cartItem: cartItems){
			if (cartItem.getId() == CartItemId)
				return cartItem;
		}
		return null;
	}

	@Override
	public void updateCartItem(CartItem cartItem) {
		long cartItemId = cartItem.getId();
		for (int i = 0; i < cartItems.size(); i++){
			if (cartItems.get(i).getId() == cartItemId){
				cartItems.set(i, cartItem);
				return;
			}
		}
	}

}
