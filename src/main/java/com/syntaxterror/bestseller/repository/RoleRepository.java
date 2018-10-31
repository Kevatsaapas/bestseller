package com.syntaxterror.bestseller.repository;

import com.syntaxterror.bestseller.model.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository  extends CrudRepository<Role, Long>{
    Role findByRooliNimi(String rooliNimi);
}
