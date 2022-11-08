package com.preciseIT.webapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/h2-console/**");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/error",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/users/*",
                        "/users",
                        "/h2-console**",
                        "/h2-console",
                        "/h2-console/**",
                        "/portal/login",
                        "/portal/registration",
                        "/images/*",
                        "/css/*",
                        "/contact",
                        "/js/*",
                        "/images*",
                        "/user/register/**/",
                        "/authenticate/**/")
                .permitAll()
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