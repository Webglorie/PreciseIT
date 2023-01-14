package com.preciseIT.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactType {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;


}
