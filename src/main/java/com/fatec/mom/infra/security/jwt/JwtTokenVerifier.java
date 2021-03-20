package com.fatec.mom.infra.security.jwt;

import com.fatec.mom.infra.config.JwtConfig;
import com.fatec.mom.infra.config.JwtSecretKey;
import com.google.common.base.Strings;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Data
public class JwtTokenVerifier extends OncePerRequestFilter {

    private static final String AUTHORITIES = "Authorities";
    private static final String AUTHORITIY = "authority";
    private static final String BEARER = "Bearer ";

    @Autowired
    private JwtSecretKey secretKey;

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(BEARER)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authorizationHeader.replace(BEARER, "");

        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey.secretKey())
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();
            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get(AUTHORITIES);

            Set<SimpleGrantedAuthority> simpleGrantedAuthorities = new HashSet<>();
            if (authorities != null) {
                for (var item : authorities) {
                    simpleGrantedAuthorities.add(new SimpleGrantedAuthority(item.get(AUTHORITIY)));
                }
            }

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    simpleGrantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            throw new IllegalStateException(String.format("Token %s cannot be trusted", token));
        }

        filterChain.doFilter(request, response);
    }
}
