package com.salesmanager.shop.store.api.v1.order;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.order.OrderCherry;
import com.salesmanager.shop.store.controller.order.facade.OraderFacadeCherry;



@RestController
@RequestMapping("/api/v1")
public class OrderApiCherry {
	
	
	@Autowired
	private OraderFacadeCherry order;
	
	
	@RequestMapping(value = { "/cherry/orders" }, method = RequestMethod.GET)
	public List<OrderCherry> getOrders(){
		return this.order.getOrders();
	}
	
	
	
	@RequestMapping(value = { "/cherry/orders" }, method = RequestMethod.POST)
	public List<OrderCherry> addOrder(@RequestBody OrderCherry order){
		return this.order.addOrders(order);
	
	}
	
	

}
