package com.src.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.src.dto.EmailDetailsDto;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class SendMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SendMailService.class);


    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    public SendMailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    public void sendSimpleMail(EmailDetailsDto details) throws Exception
    {
        try {
            LOGGER.info("send a simple mail without attachment file");
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getEmailBody());
            mailMessage.setSubject(details.getEmailSubject());
 
            javaMailSender.send(mailMessage);
        }
 
        catch (Exception exception) {
            LOGGER.error("error while sending the mail");
            throw new Exception("error while sending the mail : "
                    + exception.getMessage());
        }
    }
}