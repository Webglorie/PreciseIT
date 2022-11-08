package com.preciseIT.entities;

import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ticket")
public class Ticket extends AbstractAuditable<User, Integer> {

    public Ticket() {
    }

    public Ticket(String title, User questioner, User assignee) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
    }

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "questioner_id")
    private User questioner;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

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

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public User getQuestioner() {
        return questioner;
    }

    public void setQuestioner(User questioner) {
        this.questioner = questioner;
    }
}
