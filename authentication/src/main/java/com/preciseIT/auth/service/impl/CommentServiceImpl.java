package com.preciseIT.auth.service.impl;

import com.preciseIT.auth.service.CommentService;
import com.preciseIT.entities.Comment;
import com.preciseIT.entities.Ticket;
import com.preciseIT.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findCommentByTicket(Ticket ticket) {
       return commentRepository.findByTicket(ticket);
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }
}
