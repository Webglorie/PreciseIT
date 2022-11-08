package com.preciseIT.entities;




import com.preciseIT.enums.UserRoles;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person extends AbstractAuditable<Person, Integer> {


    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "password", nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private List<UserRoles> userRoles = List.of(UserRoles.CLIENT);

    public Person(String email, String firstName, String lastName, String password, List<UserRoles> userRoles) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRoles = userRoles;
    }

    public Person(String email, String firstName, String lastName, String password) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRoles = List.of(UserRoles.CLIENT);
    }

    public Person() {
    }


}
