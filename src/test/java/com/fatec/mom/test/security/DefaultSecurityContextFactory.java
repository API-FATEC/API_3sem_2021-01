package com.fatec.mom.test.security;

import com.fatec.mom.domain.user.ApplicationUser;
import com.fatec.mom.domain.user.authority.GrantedAuthorityImpl;
import com.fatec.mom.domain.user.authority.Role;
import com.fatec.mom.test.security.mocked.WithUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.HashSet;
import java.util.Set;

public class DefaultSecurityContextFactory implements WithSecurityContextFactory<WithUser> {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public SecurityContext createSecurityContext(WithUser withUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        ApplicationUser principal = ApplicationUser.builder()
                .username(withUser.username())
                .password(withUser.password())
                .grantedAuthorities(getGrantedAuthorities(withUser.roles()))
                .build();

        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }

    private Set<GrantedAuthorityImpl> getGrantedAuthorities(String[] roles) {
        Set<GrantedAuthorityImpl> grantedAuthorities = new HashSet<>();
        for (String r : roles) {
            Role role = Role.valueOf(r);
            if (role != null) {
                grantedAuthorities.add(new GrantedAuthorityImpl(role));
            }
        }
        return grantedAuthorities;
    }
}
