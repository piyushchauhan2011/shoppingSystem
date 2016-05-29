package org.piyush.controllers;

import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.piyush.dao.DaoFactory;

import org.springframework.web.bind.annotation.RequestMethod;
import org.piyush.models.Order;
import org.piyush.dao.OrderDao;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	private OrderDao odao = DaoFactory.getInstance().getOrderDao();
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(method=RequestMethod.GET)
    public List<Order> index() {
        return odao.getAllOrders();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Order read(@PathVariable("id") long id) {
    	return odao.getOrderById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public Order create() {
    	Order order = new Order();
    	odao.addOrder(order);
    	return order;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Order delete(@PathVariable("id") long id) {
    	Order order = odao.getOrderById(id);
    	odao.deleteOrder(id);
    	return order;
    }
}