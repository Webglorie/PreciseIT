package com.preciseIT.webapp.controller;


import org.springframework.web.bind.annotation.GetMapping;

public class FragmentsController {
    @GetMapping("/fragments")
    public String getHome() {
        return "fragments.html";
    }
}
