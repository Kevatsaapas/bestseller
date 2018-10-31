package com.syntaxterror.bestseller.service;

import com.syntaxterror.bestseller.model.Account;
import com.syntaxterror.bestseller.model.Role;
import com.syntaxterror.bestseller.repository.AccountRepository;
import com.syntaxterror.bestseller.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void save(Account account){

        Role kayttajaRooli = new Role();

        if(roleRepository.findByRooliNimi(account.getRole().getRooliNimi()) == null){
            kayttajaRooli.setRooliNimi(account.getRole().getRooliNimi());
            kayttajaRooli = roleRepository.save(kayttajaRooli);
        }else {
            kayttajaRooli = roleRepository.findByRooliNimi(account.getRole().getRooliNimi());
        }

        account.setRole(kayttajaRooli);
        account = accountRepository.save(account);
        kayttajaRooli.lisaaAccount(account);
        roleRepository.save(kayttajaRooli);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
