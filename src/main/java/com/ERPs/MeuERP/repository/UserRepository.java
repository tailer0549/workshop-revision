package com.ERPs.MeuERP.repository;


import com.ERPs.MeuERP.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
