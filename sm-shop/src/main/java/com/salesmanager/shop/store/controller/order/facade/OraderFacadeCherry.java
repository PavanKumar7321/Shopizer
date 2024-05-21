package com.salesmanager.shop.store.controller.order.facade;


import java.util.List;

import com.salesmanager.core.model.order.OrderCherry;


public interface OraderFacadeCherry {
	
	public List<OrderCherry> getOrders();
	
	public List<OrderCherry> addOrders(OrderCherry order);

	

}
