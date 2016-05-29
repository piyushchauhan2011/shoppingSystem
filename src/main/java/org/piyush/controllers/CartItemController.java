package org.piyush.controllers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.piyush.dao.DaoFactory;

import org.springframework.web.bind.annotation.RequestMethod;
import org.piyush.models.CartItem;
import org.piyush.dao.CartItemDao;

@RestController
@RequestMapping("/cartItems")
public class CartItemController {
	
	private CartItemDao cdao = DaoFactory.getInstance().getCartItemDao();
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(method=RequestMethod.GET)
    public List<CartItem> index() {
        return cdao.getAllCartItems();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public CartItem read(@PathVariable("id") long id) {
    	return cdao.getCartItemById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public CartItem create(@RequestBody CartItem CartItem) {
    	cdao.addCartItem(CartItem);
    	return CartItem;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public CartItem update(@PathVariable("id") long id, @RequestBody CartItem CartItem) {
    	CartItem.setId(id);
    	cdao.updateCartItem(CartItem);
    	return CartItem;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public CartItem delete(@PathVariable("id") long id) {
    	CartItem CartItem = cdao.getCartItemById(id);
    	cdao.deleteCartItem(id);
    	return CartItem;
    }
}