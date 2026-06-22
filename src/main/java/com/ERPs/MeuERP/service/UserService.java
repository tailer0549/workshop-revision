package com.ERPs.MeuERP.service;
import com.ERPs.MeuERP.entities.User;
import com.ERPs.MeuERP.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Find user error"));
    }

    @Transactional // Se algo der errado a transação pode ficar inconsistente, então quando algum erro acontece, o transactional usa o rollback e volta tudo.
    public User insert(User obj) {
        return repository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
      repository.deleteById(id);
    }

    @Transactional
    public User update(Long id, User obj) {
        User entity = repository.findById(id).orElseThrow(() -> new RuntimeException("error"));
        updateData(entity, obj);
        return repository.save(entity);
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
    }
}
