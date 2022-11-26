package com.preciseIT.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.AbstractAuditable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticket")
public class Ticket extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    private User questioner;

    @ManyToOne
    @JoinColumn(name = "assignee")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "product")
    private Product subjectProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    Status status;
    @ManyToOne(fetch = FetchType.LAZY)
    Priority priority;

    @Column(name = "ticketcontent", length = 4000)
    private String ticketContent;

    @OneToMany(mappedBy = "ticket", cascade = CascadeType.REMOVE)
    private Collection<Comment> comments;

    @ManyToOne(optional=false)
    @JoinColumn(name = "questioner_id", referencedColumnName = "id", insertable=false, updatable=false)
    private User user;

    public Ticket(String title, User questioner, User assignee, User createdBy) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
    }

    public Ticket(String title, User questioner, User assignee, Status status, Priority priority, String ticketContent) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
        this.ticketContent = ticketContent;
    }

    public Ticket(String title, User questioner, User assignee, Status status, Priority priority) {
        this.title = title;
        this.questioner = questioner;
        this.assignee = assignee;
        this.status = status;
        this.priority = priority;
    }

    public Ticket(String title, User createdBy) {
        this.title = title;
        this.setQuestioner(createdBy);
    }

}
