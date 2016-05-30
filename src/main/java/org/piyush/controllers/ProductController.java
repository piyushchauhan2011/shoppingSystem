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
import org.piyush.models.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.piyush.dao.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
	protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProductRepository pdao;
	
	@Context
	UriInfo uriInfo; // like an instance variable definition

    @RequestMapping(method=RequestMethod.GET)
    public List<Product> index() {
        return pdao.getAllProducts();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Product read(@PathVariable("id") long id) {
    	return pdao.getProductById(id);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public Product create(@RequestBody Product product) {
    	return pdao.insertProduct(product);
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Product update(@PathVariable("id") long id, @RequestBody Product product) {
    	product = pdao.updateProduct(id, product);
    	return product;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Product delete(@PathVariable("id") long id) {
    	Product product = pdao.getProductById(id);
    	pdao.deleteProduct(id);
    	return product;
    }
}