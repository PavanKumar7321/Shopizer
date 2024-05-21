package com.salesmanager.shop.store.controller.order.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salesmanager.core.business.repositories.order.RecentOrderCheery;
import com.salesmanager.core.model.order.OrderCherry;

@Service
public class OrderFacadeImpCherry implements OraderFacadeCherry {
	
	@Autowired
	private RecentOrderCheery recent_order;
	
	
	@Override
	public List<OrderCherry> getOrders(){
		return recent_order.findAll();
	}


	@Override
	public List<OrderCherry> addOrders(OrderCherry order) {
		
		recent_order.save(order);
		return recent_order.findAll();
		
	}


	
	

}
