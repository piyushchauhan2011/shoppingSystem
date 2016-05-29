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
import org.piyush.models.Cart;
import org.piyush.dao.CartDao;

@RestController
@RequestMapping("/carts")
public class CartController {
	
	private CartDao cdao = DaoFactory.getInstance().getCartDao();
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(method=RequestMethod.GET)
    public List<Cart> index() {
        return cdao.getAllCarts();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Cart read(@PathVariable("id") long id) {
    	return cdao.getCartById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public Cart create() {
    	Cart cart = new Cart();
    	cdao.addCart(cart);
    	return cart;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Cart update(@PathVariable("id") long id, @RequestBody long productId) {
    	Cart cart = cdao.getCartById(id);
    	cart.addProduct(productId);
    	return cart;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Cart delete(@PathVariable("id") long id) {
    	Cart cart = cdao.getCartById(id);
    	cdao.deleteCart(id);
    	return cart;
    }
}