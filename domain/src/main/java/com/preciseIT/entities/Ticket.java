package com.preciseIT.entities;

import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ticket")
public class Ticket extends AbstractAuditable<Person, Integer> {

    public Ticket() {
    }

    public Ticket(String title, Person questioner, Person assignee) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
    }

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "questioner_id")
    private Person questioner;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private Person assignee;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ticket_id")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product subjectProduct;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public Person getAssignee() {
        return assignee;
    }

    public void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    public Person getQuestioner() {
        return questioner;
    }

    public void setQuestioner(Person questioner) {
        this.questioner = questioner;
    }
}
