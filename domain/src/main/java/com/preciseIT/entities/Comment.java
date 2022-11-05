package com.preciseIT.entities;

import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "comment")
public class Comment extends AbstractAuditable<Person, UUID> {

    @Column(name = "text")
    private String text;
}
