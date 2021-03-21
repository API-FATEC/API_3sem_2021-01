package com.fatec.mom.infra.config;

import com.fatec.mom.domain.user.ApplicationUserService;
import com.fatec.mom.infra.security.jwt.JwtAuthenticationFilter;
import com.fatec.mom.infra.security.jwt.JwtTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String PATH = "/";
    private static final String INDEX = "index";
    private static final String STYLE = "/css/*";
    private static final String SCRIPTS = "/js/*";
    private static final String SWAGGER_UI = "/swagger-ui/**";
    private static final String SWAGGER_RESOURCES = "/swagger-resources/**";
    private static final String API_DOCS = "/v2/api-docs";
    private static final String CONFIGURATION = "/configuration/**";
    private static final String API = "/v3/**";
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";

    private PasswordEncoder passwordEncoder;
    private ApplicationUserService applicationUserService;
    private JwtConfig jwtConfig;
    private JwtSecretKey secretKey;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder,
                          ApplicationUserService applicationUserService,
                          JwtConfig jwtConfig,
                          JwtSecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilter(jwtAuthenticationFilter());
        http.addFilterAfter(jwtTokenVerifier(), JwtAuthenticationFilter.class);

        http.authorizeRequests()
                .antMatchers(PATH, INDEX, STYLE, SCRIPTS).permitAll()
                .antMatchers(SWAGGER_UI, API, API_DOCS, CONFIGURATION, SWAGGER_RESOURCES).permitAll()
                .antMatchers(LOGIN, LOGOUT).permitAll()
                .anyRequest().authenticated();
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setSecretKey(secretKey);
        filter.setJwtConfig(jwtConfig);
        return filter;
    }

    private JwtTokenVerifier jwtTokenVerifier() {
        JwtTokenVerifier tokenVerifier = new JwtTokenVerifier();
        tokenVerifier.setJwtConfig(jwtConfig);
        tokenVerifier.setSecretKey(secretKey);
        return tokenVerifier;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());

    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
