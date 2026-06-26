package com.ERPs.MeuERP.service;

import com.ERPs.MeuERP.entities.User;
import com.ERPs.MeuERP.repository.UserRepository;
import com.ERPs.MeuERP.service.exceptions.DatabaseException;
import com.ERPs.MeuERP.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    // Se algo der errado a transação pode ficar inconsistente, então quando algum erro acontece, o transactional usa o rollback e volta tudo.
    public User insert(User obj) {
        return repository.save(obj);
    }

    @Transactional
    public void delete(Long id) {
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (
                EmptyResultDataAccessException e) { // Capturando a exceção lançada pelo sistema e retornando a nossa exceção personalizada
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage()); // e.getMessage() -> Pega a mensagem da exceção que ocorreu
        }
    }

    @Transactional
    public User update(Long id, User obj) {
        try {
            User entity = repository.getReferenceById(id);
            updateData(entity, obj);
            return repository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User entity, User obj) {
        entity.setName(obj.getName());
        entity.setEmail(obj.getEmail());
        entity.setPhone(obj.getPhone());
    }
}
