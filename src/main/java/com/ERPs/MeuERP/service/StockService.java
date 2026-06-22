package com.ERPs.MeuERP.service;

import com.ERPs.MeuERP.entities.Stock;
import com.ERPs.MeuERP.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {

    @Autowired
    private StockRepository repository;

    public List<Stock> findAll() {
        return repository.findAll();
    }

    public Stock findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("error"));
    }

    public Stock insert(Stock obj) {
        return repository.save(obj);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Stock update(Long id, Stock obj) {
        Stock entity = repository.findById(id).orElseThrow(() -> new RuntimeException("error"));
        updateDate(entity, obj);
        return repository.save(entity);
    }

    private void updateDate(Stock entity, Stock obj) {
        entity.setName(obj.getName());
        entity.setQuantity(obj.getQuantity());
    }
}
