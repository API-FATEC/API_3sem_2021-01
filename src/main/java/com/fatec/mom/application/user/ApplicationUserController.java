package com.fatec.mom.application.user;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class ApplicationUserController {

    @GetMapping("/all")
    @PreAuthorize(value = "isAuthenticated()")
    public String getAllUsers() {
        return "All Users";
    }
}
