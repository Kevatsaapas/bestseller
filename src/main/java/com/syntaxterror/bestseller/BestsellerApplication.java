package com.syntaxterror.bestseller;

import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Objects;

@SpringBootApplication
public class BestsellerApplication {

    @Autowired
    UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(BestsellerApplication.class, args);

	}

	public void  run(String...args) throws Exception {

        String ausername = System.getenv("BESTSELLER_USERNAME");
        String apassword = System.getenv("BESTSELLER_PASSWORD");

        User user = userRepository.findByUsername(ausername);

        if (Objects.isNull(user)){

            user = new User();
            user.setUsername(ausername);
            user.setPasswordHash(apassword);

            userRepository.save(user);
		}
	}
}
