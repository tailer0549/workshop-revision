package com.ERPs.MeuERP.repository;

import com.ERPs.MeuERP.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
