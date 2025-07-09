package com.vini.piai.backend.email;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl {


    /*private final JavaMailSender emailSender;
    private final String email = "myheartappmestrado@gmail.com";

    public void passwordResetCode(String to, String code) {
        String subject = "Redefinição de Senha - Código";
        String text = "Digite o código " + code + " na tela do aplicativo para prosseguir com a redefinição de senha";
        sendSimpleMessage(to, subject, text);
    }

    private void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(email);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }*/
}
