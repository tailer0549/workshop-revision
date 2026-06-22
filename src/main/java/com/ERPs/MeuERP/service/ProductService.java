package com.ERPs.MeuERP.service;

import com.ERPs.MeuERP.entities.Product;
import com.ERPs.MeuERP.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    public Product insert(Product obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Product update(Long id, Product obj) {
        Product entity = repository.findById(id).orElseThrow(() -> new RuntimeException("error"));
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(Product entity, Product obj) {
        entity.setCategory(obj.getCategory());
        entity.setName(obj.getName());
        entity.setPrice(obj.getPrice());
    }
}
