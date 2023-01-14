package com.preciseIT.controllers;

import com.preciseIT.auth.service.CommentService;
import com.preciseIT.auth.service.TicketService;
import com.preciseIT.auth.service.UserService;
import com.preciseIT.entities.Comment;
import com.preciseIT.entities.User;
import com.preciseIT.enums.AuthenticationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    TicketService ticketService;

    @Autowired
    UserService userService;

    @PostMapping("/portal/ticket/{ticketid}/addcomment/{comment}")
    public String createNewPost(@PathVariable String ticketid, @PathVariable String comment) {
        Comment comment1 = new Comment();
        comment1.setTicket(ticketService.getTicketById(ticketid));
        comment1.setText(comment);
        comment1.setUser(userService.findById("4"));

        if (comment.isEmpty()) {
            return "redirect:/portal/ticket/" + comment1.getTicket().getId() + "?status=failed";

        } else {
            commentService.save(comment1);
            return "redirect:/portal/ticket/" + comment1.getTicket().getId() + "?status=success";
        }
    }



}
