package com.salesmanager.core.business.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;

import com.salesmanager.core.model.order.OrderCherry;

public interface RecentOrderCheery extends JpaRepository<OrderCherry, Long>{

}
