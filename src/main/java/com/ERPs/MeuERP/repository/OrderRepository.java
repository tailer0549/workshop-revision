package com.ERPs.MeuERP.repository;

import com.ERPs.MeuERP.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
}
