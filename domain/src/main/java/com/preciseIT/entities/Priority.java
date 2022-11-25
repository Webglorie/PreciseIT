package com.preciseIT.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "priorities")
public class Priority {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    int id;
    String name;


}