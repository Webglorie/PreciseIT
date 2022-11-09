package com.preciseIT.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
public class Comment extends AbstractAuditable<User, Integer> {

    @Column(name = "id")
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "text")
    private String text;


}
