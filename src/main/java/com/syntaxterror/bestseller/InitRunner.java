package com.syntaxterror.bestseller;


import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class InitRunner implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Override
    public void  run(String...args) throws Exception {

        String ausername = System.getenv("BESTSELLER_USERNAME");
        String apassword = System.getenv("BESTSELLER_PASSWORD");

        User user = userRepository.findByUsername(ausername);

        if (Objects.isNull(user)) {

            user = new User();
            user.setUsername(ausername);
            user.setPasswordHash(apassword);

            userRepository.save(user);
        }
    }
}