package com.syntaxterror.bestseller.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 @Autowired
private DataSource dataSource();
*/

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/css/style.css",
                        "/img/*",
                        "/arviointi",
                        "/index",
                        "/uusi/{kilpailuId}/{lohkoId}",
                        "/uusitest/{kilpailuId}/{lohkoId}",
                        "/tallenna/{kilpailuId}/{lohkoId}",
                        "/datat/{kilpailuId}",
                        "/tarkastelu/{arviointiId}",
                        "/poistaarviointi/{arviointiId}",
                        "/luodatat/{kilpailuId}",
                        "/kilpailuvalittu/",
                        "/lohkovalittu/",
                        "/testaus",
                        "/tuomarointi/",
                        "/luokilpailija/{kilpailuId}",
                        "/editkilpailija/{kilpailijaId}",
                        "/tallennakilpailija",
                        "/poistakilpailija/{kilpailijaId}",
                        "/poistatuomari/{tuomariId}",
                        "/tuomarivalikko",
                        "/pisteet/{kilpailuId}",
                        "/luokilpailija/{kilpailuId}",
                        "/editkilpailija/{kilpailijaId}",
                        "/tallennakilpailija",
                        "/poistakilpailija/{kilpailijaId}",
                        "/poistatuomari/{tuomariId}",
                        "/luotuomari/{kilpailuId}",
                        "/edittuomari/{tuomariId}",
                        "/tallennatuomari").permitAll()
                .and()
                .authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/index")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
}
