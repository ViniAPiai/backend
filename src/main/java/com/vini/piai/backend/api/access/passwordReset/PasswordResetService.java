package com.vini.piai.backend.api.access.passwordReset;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vini.piai.backend.api.access.passwordReset.dto.*;
import com.vini.piai.backend.api.access.user.UserService;
import com.vini.piai.backend.api.utils.CodeGenerator;
import com.vini.piai.backend.security.exception.CodeNotMatchException;
import com.vini.piai.backend.security.exception.PasswordResetNotFoundException;
import com.vini.piai.backend.security.exception.PasswordResetTimeExpiredBadRequestException;
import com.vini.piai.backend.security.exception.TokenNotMatchException;
import com.vini.piai.backend.security.jwt.JwtService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PasswordResetService {

    private final PasswordResetRepository passwordResetRepository;
    private final UserService userService;
    private final CodeGenerator codeGenerator;
    private final JwtService jwtService;

    /**
     * The first step of the password reset.
     * @param dto the user information for password redefinition, in this case, his email
     * @return a message saying that the code was sent by email
     */
    public PasswordResetDtoEmailResponse passwordResetEmail(PasswordResetDtoEmailRequest dto) {
        PasswordReset passwordReset = findByUserEmail(dto.email());
        passwordReset.setCode(codeGenerator.generateCode());
        passwordReset = passwordResetRepository.save(passwordReset);
        // TODO put here the call for the emailService
        return PasswordResetDtoEmailResponse.builder().message("The code was sent by email").build();
    }

    /**
     * The second step of the password reset
     * @param dto the user information for checking the code, such as email and code
     * @return the user email and the jwt token for
     */
    public PasswordResetDtoCodeResponse passwordResetCode(PasswordResetDtoCodeRequest dto) {
        PasswordReset passwordReset = findByUserEmail(dto.email());
        validatePasswordReset(passwordReset, dto.email(), dto.code());
        return PasswordResetDtoCodeResponse.builder()
                .email(dto.email())
                .token(jwtService.generateAnonymousToken(dto.email()))
                .build();
    }

    /**
     * The third step of the password reset
     * @param dto the user information for the password reset, such as email, code and password
     * @param token the token pass by the previous step
     * @return a message saying that the password was redefined successfully
     */
    public PasswordResetDtoResponse passwordReset(PasswordResetDtoRequest dto, String token) {
        String username = jwtService.extractUsername(token);
        if(!username.equals(dto.email())) {
            throw new TokenNotMatchException();
        }
        PasswordReset passwordReset = findByUserEmail(dto.email());
        validatePasswordReset(passwordReset, dto.email(), dto.code());
        userService.updatePassword(dto.email(), dto.password());
        return PasswordResetDtoResponse.builder().message("The password was redefined successfully").build();
    }

    /**
     * Method for validating the password reset, by code and time
     * @param passwordReset the password reset for comparison
     * @param email the user email
     * @param code the code
     * @throws PasswordResetTimeExpiredBadRequestException the password time is expired
     * @throws CodeNotMatchException the code passed is not equal to the one in the password reset entity
     */
    private void validatePasswordReset(PasswordReset passwordReset, String email, String code) throws PasswordResetTimeExpiredBadRequestException, CodeNotMatchException {
        if(passwordReset.getTimeLimit().isBefore(LocalDateTime.now())) {
            throw new PasswordResetTimeExpiredBadRequestException(email);
        }
        if(!passwordReset.getCode().equals(code)){
            throw new CodeNotMatchException(email);
        }
    }

    /**
     * Method for finding a password reset by user email
     * @param email the user email
     * @return the password reset founded
     */
    private PasswordReset findByUserEmail(String email){
        Optional<PasswordReset> opt = passwordResetRepository.findByUserEmail(email);
        if(opt.isEmpty()){
            throw new PasswordResetNotFoundException(email);
        }
        return opt.get();
    }


}
