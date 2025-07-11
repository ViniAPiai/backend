package com.vini.piai.backend.api.access.user;

import com.vini.piai.backend.api.utils.ExceptionEnum;
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

    /**
     * Method for checking if the email is available
     * @param email the email for being checked
     * @return true if the email is not available and false if it is
     */
    public Boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Method for checking if the document number is available
     * @param documentNumber the document number for being checked
     * @return true if the document number is not available and false if it is
     */
    public Boolean existsByDocumentNumber(String documentNumber) {
        return userRepository.findByDocumentNumber(documentNumber).isPresent();
    }

    /**
     * Method for encoding the password
     * @param password the password to be encoded
     * @return the encoded password
     */
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    /**
     * Method for finding a user by email
     * @param email the email
     * @return the user found
     * @throws UsernameNotFoundException the user isn't found with this email
     */
    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(ExceptionEnum.USER_NOT_FOUND.getTopic() + ": " + email));
    }

    /**
     * Method for find the user logged by spring security context
     * @return the current user
     * @throws UsernameNotFoundException the user isn't found with the security context
     */
    public User current() throws UsernameNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UsernameNotFoundException(ExceptionEnum.USER_NOT_FOUND.getTopic());
        }
        return (User) authentication.getPrincipal();
    }

    /**
     * Method for updating the user password
     * @param email the user email
     * @param password the user new password
     */
    public void updatePassword(String email, String password) {
        User user = findByEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    /**
     * Method for saving the user
     * @param user the user to be saved
     * @return the saved user
     */
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = userRepository.findByEmail(username);
        return userDetail.orElseThrow(() -> new UsernameNotFoundException(ExceptionEnum.USER_NOT_FOUND.getTopic() + ": " + username));
    }
}
