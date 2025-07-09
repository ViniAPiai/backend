package com.vini.piai.backend.api.access.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    public Boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.findByDocumentNumber(documentNumber).isPresent();
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public User current() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException("User not found");
        }
        return (User) authentication.getPrincipal();
    }

    public void updatePassword(String email, String password) {
        User user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByEmail(username);
        return userDetail.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
