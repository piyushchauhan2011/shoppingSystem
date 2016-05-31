package org.piyush.controllers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;
import org.piyush.models.Cart;
import org.piyush.models.Order;
import org.piyush.models.Product;
import org.piyush.models.Success;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.piyush.dao.CartRepository;
import org.piyush.dao.OrderRepository;

@RestController
@RequestMapping("/carts")
public class CartController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CartRepository cdao;
    
    @Autowired
    private OrderRepository odao;
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Cart index(@PathVariable("id") long id) {
        return cdao.getCartById(id);
    }
    
    @RequestMapping(value="/{id}/products", method=RequestMethod.GET)
    public List<Product> cartProducts(@PathVariable("id") long id) {
        return cdao.getCartProducts(id);
    }
    
    @RequestMapping(value="/{id}/products", method=RequestMethod.PUT)
    public Cart index(@PathVariable("id") long id, @RequestBody Product product) {
    	Cart c = cdao.getCartById(id);
        return cdao.insertProduct(c, product);
    }
    
    @RequestMapping(value="/{id}/products/{cartItemId}", method=RequestMethod.DELETE)
    public Cart index(
    		@PathVariable("id") long id,
    		@PathVariable("cartItemId") long cartItemId) {
    	Cart c = cdao.getCartById(id);
        return cdao.deleteCartItem(c, cartItemId);
    }
    
    @RequestMapping(value="/{id}/checkout", method=RequestMethod.GET)
    public Success checkout(@PathVariable("id") long id) {
    	Cart c = cdao.getCartById(id);
    	return new Success(c.isValid());
    }
    
    @RequestMapping(value="/{id}/confirm", method=RequestMethod.GET)
    public Order confirm(@PathVariable("id") long id) {
    	Cart c = cdao.getCartById(id);
    	Order o = odao.insertOrder(c, "partially-shipped", "Unit 11");
    	return o;
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public Cart create() {
    	Cart cart = new Cart();
    	cdao.createCart(cart);
    	return cart;
    }
}