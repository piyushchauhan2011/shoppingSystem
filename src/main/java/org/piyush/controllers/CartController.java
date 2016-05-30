package org.piyush.controllers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.RequestMethod;
import org.piyush.models.Cart;
import org.piyush.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.piyush.dao.CartRepository;

@RestController
@RequestMapping("/carts")
public class CartController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private CartRepository cdao;
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(value="/{id}/products", method=RequestMethod.GET)
    public List<Product> index(@PathVariable("id") long id) {
        return cdao.getCartProducts(id);
    }
    
    @RequestMapping(value="", method=RequestMethod.POST)
    public Cart create() {
    	Cart cart = new Cart();
    	cdao.createCart(cart);
    	return cart;
    }
}