package com.fatec.mom.test.security.mocked;

import com.fatec.mom.test.security.DefaultSecurityContextFactory;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD, ElementType.TYPE})
@WithSecurityContext(factory = DefaultSecurityContextFactory.class)
public @interface WithUser {

    String username() default "wcaetano";
    String password() default "password";
    String[] roles() default "EDITOR";
}
