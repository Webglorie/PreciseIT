package com.preciseIT.entities;

import com.preciseIT.enums.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="users")
public class User extends AbstractAuditable<User, Integer> {


    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "secret")
    private String secret;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    public User(String email, String firstName, String lastName, String password, Role role, String secret) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
        this.secret = secret;
    }

    public User(String email, String password, String secret, Role role) {
        this.email = email;
        this.password = password;
        this.secret = secret;
        this.role = role;
    }

    public User(String email, String firstName, String lastName, String password, Role role) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.role = role;
    }

    public User(String email, String firstName, String lastName, String password, String secret, Role role) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.secret = secret;
        this.role = role;
    }

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }

}