package com.vini.piai.backend.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.api.access.user.UserRepository;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findByEmail(username);
        return userDetail.orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

}