package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

//Lavet af Emil
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username =?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/medarbejder/**").hasRole("ADMIN")
                .antMatchers("/faktura/**").hasAnyRole("ADMIN", "BOOKKEEPER")
                .antMatchers("/autocamper/oversigt").hasAnyRole("ADMIN", "LEAD", "SALES")
                .antMatchers("/autocamper/**").hasAnyRole("ADMIN", "LEAD")
                .antMatchers("/kunde/**").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/reservation/**").hasAnyRole("ADMIN","LEAD", "SALES")
                .antMatchers("/", "/css/**", "/img/**").permitAll()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index", false)
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
            .exceptionHandling()
                .accessDeniedPage("/adgang-nægtet");
    }

    // Spring security needs to be provided with a password encoder. This one does nothing, but we do provide it.
    // Proper password encoding is one of the things we didn't finish in time.
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}