package com.fatec.mom.application;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * A classe <code>SimpleSwagger</code> testa a página do Swagger
 *
 * @author Tobias Lino
 * @version 0.1 15/03/2021
 */
@RestController
@RequestMapping("/hello")
public class SimpleSwagger {

    @GetMapping("/world")
    @ApiOperation(value = "Retorna um Hello World")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping("/world/admin")
    public String helloAdmin() {
        return "Hello Admin";
    }
}
