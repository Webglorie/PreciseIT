package com.preciseIT.entities;




import com.preciseIT.enums.UserRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="`user`")
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "userRole", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(value = EnumType.STRING)
    private List<UserRoles> userRoles = List.of(UserRoles.CLIENT);

    public User(String email, String firstName, String lastName, String password, List<UserRoles> userRoles, String secret) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRoles = userRoles;
        this.secret = secret;
    }

    public User(String email, String firstName, String lastName, String password) {
        this.setCreatedDate(LocalDateTime.now());
        this.setLastModifiedDate(LocalDateTime.now());
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userRoles = List.of(UserRoles.CLIENT);
    }

    public User(String email, String password, String secret) {
        this.email = email;
        this.password = password;
        this.secret = secret;
    }
}
