package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username =?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/employee/create").hasRole("ADMIN")
                .antMatchers("/employee/edit").hasRole("ADMIN")
                .antMatchers("/employee/overview").hasRole("ADMIN")
                .antMatchers("/motorhome/creater").hasAnyRole("ADMIN", "LEAD")
                .antMatchers("/motorhome/edit").hasAnyRole("ADMIN", "LEAD")
                .antMatchers("/motorhome/overview").hasAnyRole("ADMIN", "LEAD")
                .antMatchers("/customer/create").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/customer/edit").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/customer/overview").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/reservation/create").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/reservation/edit").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/reservation/overview").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
