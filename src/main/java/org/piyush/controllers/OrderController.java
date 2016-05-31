package org.piyush.controllers;

//import java.util.List;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

//import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//import org.springframework.web.bind.annotation.RequestMethod;
//import org.piyush.models.Order;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Context
	UriInfo uriInfo; // like an instance variable definition
}