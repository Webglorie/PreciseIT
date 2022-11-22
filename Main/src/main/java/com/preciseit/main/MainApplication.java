
package com.preciseit.main;

import com.preciseIT.services.EmailService;
import com.preciseIT.services.EmailServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.mail.MessagingException;

@SpringBootApplication
@EntityScan(basePackages = {"com.preciseIT.entities", "com.preciseIT.enums"})
@EnableJpaRepositories(basePackages = {"com.preciseIT.repos"})
@ComponentScan(basePackages = {"com.preciseIT.controllers", "com.preciseIT.webapp.controller", "com.preciseIT", "com.preciseIT.auth", "com.preciseIT.repos"})
public class MainApplication {


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}