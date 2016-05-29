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
import org.piyush.models.Product;
import org.piyush.dao.ProductDao;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductDao pdao = DaoFactory.getInstance().getProductDao();
	
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
    	pdao.addProduct(product);
    	return product;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public Product update(@PathVariable("id") long id, @RequestBody Product product) {
    	product.setProductId(id);
    	pdao.updateProduct(product);
    	return product;
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public Product delete(@PathVariable("id") long id) {
    	Product product = pdao.getProductById(id);
    	pdao.deleteProduct(id);
    	return product;
    }
}