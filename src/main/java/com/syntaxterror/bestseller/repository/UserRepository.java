package com.syntaxterror.bestseller.repository;

import org.springframework.data.repository.CrudRepository;

import com.syntaxterror.bestseller.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User deleteByRooliId(Long rooliId);
    User findByRooliId(Long rooliId);
}
