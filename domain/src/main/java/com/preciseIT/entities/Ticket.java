package com.preciseIT.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket extends AbstractAuditable<User, Integer> {
    public Ticket(String title, User questioner, User assignee, User createdBy) {
        this.setCreatedBy(createdBy);
        this.setCreatedDate(LocalDateTime.now());
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
    }

    public Ticket(String title, User questioner, User assignee, User createdBy, Status status, Priority priority) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
        this.setCreatedBy(createdBy);
        this.setCreatedDate(LocalDateTime.now());
    }

    public Ticket(String title, User createdBy) {
        this.title = title;
        this.setCreatedBy(createdBy);
        this.setQuestioner(createdBy);
        this.setCreatedDate(LocalDateTime.now());
    }

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "questioner")
    private User questioner;

    @ManyToOne
    @JoinColumn(name = "assignee")
    private User assignee;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ticket")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product subjectProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    Priority priority;


}
