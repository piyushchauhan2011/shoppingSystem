package org.piyush.controllers;

import java.util.List;
//import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.piyush.models.Product;
import org.piyush.dao.ProductDao;
import org.piyush.dao.ProductDaoMemImpl;

@RestController
@RequestMapping("/products")
public class ProductController {

//    private final AtomicLong counter = new AtomicLong();
    private ProductDao pdao = new ProductDaoMemImpl();

    @RequestMapping(method=RequestMethod.GET)
    public List<Product> products() {
        return pdao.getAllProducts();
    }
    
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Product product(@PathVariable("id") long id) {
    	return pdao.getProductById(id);
    }
}