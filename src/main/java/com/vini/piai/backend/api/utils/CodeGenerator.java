package com.vini.piai.backend.api.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.vini.piai.backend.environment.CodeGeneratorEnvironment;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class CodeGenerator {

    private final CodeGeneratorEnvironment codeGeneratorEnvironment;

    /**
     * Method for generating the code with the number of characters set in the code generation environment
     * for password redefinition
     * @return the code
     */
    public String generateCode() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom RANDOM = new SecureRandom();
        int size = Integer.parseInt(codeGeneratorEnvironment.getPasswordResetCodeSize());
        StringBuilder code = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

    public String generatePassword() {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom RANDOM = new SecureRandom();
        StringBuilder code = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            int randomIndex = RANDOM.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(randomIndex));
        }
        return code.toString();
    }

}
