package com.preciseit.main;

import com.preciseIT.DummyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.preciseIT.entities", "com.preciseIT.enums"})
@EnableJpaRepositories(basePackages = {"com.preciseIT.repos"})
@ComponentScan(basePackages = {"com.preciseIT.controllers", "com.preciseIT.webapp.controller"})
public class MainApplication {


    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
