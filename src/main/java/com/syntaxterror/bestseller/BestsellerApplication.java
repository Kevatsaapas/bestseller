package com.syntaxterror.bestseller;

import com.syntaxterror.bestseller.model.User;
import com.syntaxterror.bestseller.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@SpringBootApplication
public class BestsellerApplication {


	public static void main(String[] args) {
		SpringApplication.run(BestsellerApplication.class, args);

	}

}

