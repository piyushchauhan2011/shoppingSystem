package org.piyush.controllers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.piyush.dao.OrderRepository;
import org.piyush.models.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderRepository odao;
	
	@Context
	UriInfo uriInfo; // like an instance variable definition
	
    @RequestMapping(method=RequestMethod.GET)
    public List<Order> index() {
        return odao.getAllOrders();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Order index(@PathVariable("id") long id) {
        return odao.getOrderById(id);
    }
}