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

    public PasswordResetDtoEmailResponse passwordResetEmail(PasswordResetDtoEmailRequest dto) {
        PasswordReset passwordReset = findByUserEmail(dto.email());
        passwordReset.setCode(codeGenerator.generateCode());
        passwordReset = passwordResetRepository.save(passwordReset);
//        emailService.passwordResetCode(dto.email(), passwordReset.getCode());
        return PasswordResetDtoEmailResponse.builder().message("O código foi enviado por e-mail").build();
    }

    public PasswordResetDtoCodeResponse passwordResetCode(PasswordResetDtoCodeRequest dto) {
        PasswordReset passwordReset = findByUserEmail(dto.email());
        validatePasswordReset(passwordReset, dto.email(), dto.code());
        return PasswordResetDtoCodeResponse.builder()
                .email(dto.email())
                .token(jwtService.generateAnonymousToken(dto.email()))
                .build();
    }

    public PasswordResetDtoResponse passwordReset(PasswordResetDtoRequest dto, String token) {
        String username = jwtService.extractUsername(token);
        if(!username.equals(dto.email())) {
            throw new TokenNotMatchException();
        }
        PasswordReset passwordReset = findByUserEmail(dto.email());
        validatePasswordReset(passwordReset, dto.email(), dto.code());
        userService.updatePassword(dto.email(), dto.password());
        return PasswordResetDtoResponse.builder().message("Senha Redefinida com Sucesso").build();
    }

    private void validatePasswordReset(PasswordReset passwordReset, String email, String code) {
        if(passwordReset.getTimeLimit().isBefore(LocalDateTime.now())) {
            throw new PasswordResetTimeExpiredBadRequestException(email);
        }
        if(!passwordReset.getCode().equals(code)){
            throw new CodeNotMatchException(email);
        }
    }

    private PasswordReset findByUserEmail(String email){
        Optional<PasswordReset> opt = passwordResetRepository.findByUserEmail(email);
        if(opt.isEmpty()){
            throw new PasswordResetNotFoundException(email);
        }
        return opt.get();
    }


}
