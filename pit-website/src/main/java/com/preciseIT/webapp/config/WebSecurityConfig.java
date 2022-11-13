package com.preciseIT.webapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@EnableJpaRepositories
@EnableJpaAuditing
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
                        "/users/**",
                        "/users/*",
                        "/users",
                        "/h2-console**",
                        "/h2-console",
                        "/h2-console/**",
                        "/portal/registration",
                        "/images/*",
                        "/css/*",
                        "/contact",
                        "/js/*",
                        "/images*",
                        "/user/register/**/",
                        "/portal/users",
                        "/portal/logout-succes",
                        "/portal/create-ticket",
                        "/portal/tickets",
                        "/portal/create-ticket/**",
                        "/tickets/create-ticket",
                        "/tickets/create-ticket/**",
                        "/authenticate/**/",
                        "/authenticate/**/**",
                        "/authenticate/*/*")
                .permitAll()
                .antMatchers(
                        "/portal/admin/**",
                        "/portal/tickets/create-ticket/**",
                        "/portal/tickets/create-ticket/**/**",
                        "/portal/tickets/**")
                .hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/portal/login")
                .defaultSuccessUrl("/portal/dashboard")
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override public AuthenticationManager authenticationManagerBean() throws Exception { return super.authenticationManagerBean(); }

}