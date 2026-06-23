package com.ERPs.MeuERP.repository;

import com.ERPs.MeuERP.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
