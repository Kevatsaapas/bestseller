package com.syntaxterror.bestseller.repository;

import com.syntaxterror.bestseller.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUsername(String username);
}
