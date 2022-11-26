package com.preciseIT.auth.service;

import com.preciseIT.entities.Comment;
import com.preciseIT.entities.Ticket;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentByTicket(Ticket ticket);
    Comment save(Comment comment);
}
