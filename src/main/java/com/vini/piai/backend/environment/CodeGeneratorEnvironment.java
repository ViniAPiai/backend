package com.vini.piai.backend.environment;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Data
@Service
public class CodeGeneratorEnvironment {

    @Value(value = "${password.reset.code.size}")
    private String passwordResetCodeSize;

}
