package com.vini.piai.backend.api.access.auth;

import com.vini.piai.backend.api.access.refreshToken.RefreshTokenService;
import com.vini.piai.backend.api.access.role.RoleEnum;
import com.vini.piai.backend.api.access.role.RoleService;
import com.vini.piai.backend.api.access.user.UserService;
import com.vini.piai.backend.api.access.user.dto.UserDtoInsert;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.vini.piai.backend.api.access.user.User;
import com.vini.piai.backend.api.access.auth.dto.AuthDtoSignInRequest;
import com.vini.piai.backend.api.access.auth.dto.AuthDtoSignInResponse;
import com.vini.piai.backend.security.jwt.JwtService;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;
    private final RoleService roleService;
    private final JwtService jwtService;

    public AuthService(AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService,
                       UserService userService, RoleService roleService, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.userService = userService;
        this.roleService = roleService;
        this.jwtService = jwtService;
    }

    /**
     * Method for the user to sign in in the application
     * @param dto the information for the user to sign in, such as email and password
     * @return the token and the expiration date
     */
    public AuthDtoSignInResponse signIn(AuthDtoSignInRequest dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                dto.email(), dto.password()
        ));
        if (authentication.isAuthenticated()) {
            User user = (User) authentication.getPrincipal();
            String token = jwtService.generateToken(user);
            refreshTokenService.createOrUpdate(user);
            return new AuthDtoSignInResponse(token, jwtService.extractExpiration(token).toInstant());
        }else {
            throw new RuntimeException("Authentication failed");
        }
    }

    /**
     * Method for creating a new user
     * @param dto the information of the user, such as full name, email, document number and password
     * @return the created user
     */
    public User signUpCommonUser(UserDtoInsert dto) {
        if (userService.existsByEmail(dto.email())) {
            throw new RuntimeException("Email already exists");
        }
        if (userService.existsByDocumentNumber(dto.documentNumber())) {
            throw new RuntimeException("Document number already exists");
        }
        return userService.save(User.builder()
                .email(dto.email())
                .fullName(dto.fullName())
                .password(userService.encodePassword(dto.password()))
                .documentNumber(dto.documentNumber())
                .role(roleService.findByName(RoleEnum.USER))
                .build());
    }

}
