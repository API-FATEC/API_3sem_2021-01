package com.fatec.mom.test.security;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@WithSecurityContext(factory = DefaultSecurityContextFactory.class)
public @interface WithUser {

    String username() default "tslino";
    String password() default "password";
    String[] authorities() default "ADMIN";
}
