package com.fatec.mom.application.user;

import com.fatec.mom.domain.user.ApplicationUser;
import com.fatec.mom.domain.user.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class ApplicationUserController {

    @Autowired
    private ApplicationUserService userService;

    @GetMapping("/all")
    @PreAuthorize(value = "isAuthenticated()")
    public String getAllUsers() {
        ApplicationUser loggedUser = userService.getLoggedUser();
        System.out.println(loggedUser);
        return "All Users";
    }

    @PostMapping("/insert")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ApplicationUser insertNewUser(@RequestBody ApplicationUser user) {
        return userService.save(user);
    }

    @PutMapping("/update/active/{id}")
    public ApplicationUser activateUser(@RequestBody ApplicationUser applicationUser, @PathVariable Long id) {
        return userService.activateUser(applicationUser, id);
    }
}
