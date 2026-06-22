package com.ERPs.MeuERP.repository;

import com.ERPs.MeuERP.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
