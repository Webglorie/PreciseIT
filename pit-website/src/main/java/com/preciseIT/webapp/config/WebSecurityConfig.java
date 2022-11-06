package com.preciseIT.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/portal/login", "/portal/registration", "/images/*", "/css/*", "/contact", "/js/*", "/images*", "/user/register/**/", "/authenticate/**/").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/portal/login")
                .defaultSuccessUrl("/portal")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}