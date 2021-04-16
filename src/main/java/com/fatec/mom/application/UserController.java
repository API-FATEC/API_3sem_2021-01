package com.fatec.mom.application;

import com.fatec.mom.domain.user.User;
import com.fatec.mom.domain.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * A classe <code>UserController</code> é responsável por fornecer uma api para realização a tabela MOM_USER.
 *
 * @author Devanir
 * @version v01 15/04/2021
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "Adiciona um User no banco")
    public ResponseEntity<User> addUser(
            @RequestBody User user) {
        var newUser = userService.addUser(user);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(newUser);
    }
}