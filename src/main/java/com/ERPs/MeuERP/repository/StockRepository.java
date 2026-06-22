package com.ERPs.MeuERP.repository;

import com.ERPs.MeuERP.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
