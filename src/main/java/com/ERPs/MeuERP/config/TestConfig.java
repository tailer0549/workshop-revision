package com.ERPs.MeuERP.config;

import com.ERPs.MeuERP.entities.User;
import com.ERPs.MeuERP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

import static java.util.Arrays.asList;

@Configuration // Diz ao Spring que essa classe é uma classe de configuração
@Profile("test") // Diz que essa classe é uma classe de configuração específica para o perfil de teste
public class TestConfig implements CommandLineRunner {

    @Autowired // Injeção de dependência automatica
    private UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1,u2));

    }
}
