package com.example.JobPortal.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

    @Autowired
    UserDetailsService userDetailsServiceObject;

    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsServiceObject);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.cors().disable();
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/add-job").hasAuthority("admin")
                .antMatchers("/display-All-job").permitAll()
                .antMatchers("/display-By-JobName/{name}").permitAll()
                .antMatchers("/display-By-JobType/{jobType}").permitAll()
                .antMatchers("/update/{id}").hasAuthority("admin")
                .antMatchers("/delete-by-id/{id}").hasAuthority("admin")
                .antMatchers("delete-all-job").hasAuthority("admin")
                .antMatchers("/add-Application").hasAuthority("user")
                .antMatchers("/display-All-Application").hasAuthority("admin")
                .antMatchers("/display-By-application-id/{id}").hasAuthority("admin")
                .antMatchers("/update-application/{id}").hasAuthority("user")
                .antMatchers("/delete-by-application-id/{id}").hasAuthority("admin")
                .antMatchers("delete-all-Application").hasAuthority("admin")
                .anyRequest().authenticated().and().httpBasic();
    }
}
