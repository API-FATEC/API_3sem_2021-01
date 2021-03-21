package com.fatec.mom.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserService implements UserDetailsService {

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", username)));
    }

    public ApplicationUser save(ApplicationUser user) {
        final String password = user.getPassword();
        final String encryptedPassword = passwordEncoder.encode(password);

        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public ApplicationUser getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String user = authentication == null ? null : authentication.getPrincipal().toString();

        return userRepository.findByUsername(user).orElseThrow(() -> new UsernameNotFoundException(String.format("Username %s not found", user)));
    }

    public ApplicationUser activateUser(final ApplicationUser newUser, final Long id) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setEnabled(true);
                    user.setGrantedAuthorities(newUser.getGrantedAuthorities());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(newUser.getPassword());
                    return this.save(user);
                }).orElseThrow();
    }
}
