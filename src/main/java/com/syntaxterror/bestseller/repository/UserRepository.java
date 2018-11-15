package com.syntaxterror.bestseller.repository;

import java.util.List;

import com.syntaxterror.bestseller.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    User deleteByRooliId(Long rooliId);
    User findByRooliId(Long rooliId);
}
