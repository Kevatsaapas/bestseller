package com.syntaxterror.bestseller.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.syntaxterror.bestseller.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	User deleteByRooliId(Long rooliId);

	User findByRooliId(Long rooliId);

	List<User> findByRooli(String rooli);
	
	List<User> findByRole(String role);
}
