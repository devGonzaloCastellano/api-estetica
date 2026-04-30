package com.estetica.api_estetica.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendTemporaryCredentials(String toEmail, String username, String tempPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAddress);
        message.setTo(toEmail);
        message.setSubject("Bienvenido - Tus credenciales de acceso");
        message.setText("""
                Hola,
                
                Tu cuenta fue creada exitosamente. Tus credenciales temporales son:
                
                Usuario: %s
                Contraseña: %s
                
                Por seguridad, te recomendamos cambiar tu contraseña al ingresar por primera vez.
                
                Saludos,
                Equipo de Estética
                """.formatted(username, tempPassword));
        mailSender.send(message);
    }
}
