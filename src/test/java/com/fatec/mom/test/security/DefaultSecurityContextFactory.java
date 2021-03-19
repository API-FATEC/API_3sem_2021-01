package com.fatec.mom.test.security;

import com.fatec.mom.domain.user.ApplicationUser;
import com.fatec.mom.domain.user.authority.GrantedAuthorityImpl;
import com.fatec.mom.domain.user.authority.Role;
import com.fatec.mom.infra.security.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.HashSet;
import java.util.Set;

public class DefaultSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public SecurityContext createSecurityContext(WithUser annotatedUser) {
        String username = annotatedUser.username();
        String password = passwordEncoder.encode(annotatedUser.password());

        GrantedAuthorityImpl authority = getAuthority(annotatedUser.authorities());

        final Authentication authentication = new UsernamePasswordAuthenticationToken(username, password, getGrantedAuthorities(authority));
        final SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        return context;
    }

    private GrantedAuthorityImpl getAuthority(final String[] roles) {
        GrantedAuthorityImpl authority = new GrantedAuthorityImpl();
        for (String name : roles) {
            final Role role = Role.valueOf(name);
            if (role != null) {
                authority = new GrantedAuthorityImpl(role);
            }
        }
        return authority;
    }

    private Set<GrantedAuthorityImpl> getGrantedAuthorities(final GrantedAuthorityImpl authority) {
        Set<GrantedAuthorityImpl> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(authority);
        return grantedAuthorities;
    }
}
