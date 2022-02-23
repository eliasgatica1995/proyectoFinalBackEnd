package com.entrega.service.impl;

import com.entrega.service.EmailSender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Getter
@Setter
@Configuration
@ConfigurationProperties
public class EmailSenderImpl implements EmailSender {
    @Autowired
    private JavaMailSender mailSender;
    @Value(value = "${spring.mail.username}")
    private String origen;
    @Override
    public void sendEmail(String body,String subject) {
        //google manage account, security, app passwords
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setFrom(origen);
        mensaje.setTo(origen);
        mensaje.setText(body);
        mensaje.setSubject(subject);
        mailSender.send(mensaje);
    }
}
