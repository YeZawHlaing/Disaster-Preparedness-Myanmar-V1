package com.backend.v1.service.serviceImpl;

import com.backend.v1.common.utils.EmailDetails;
import com.backend.v1.exceptions.EmailServiceErrorException;
import com.backend.v1.service.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    private String sender = "cvnxsolution@gmail.com";

    @Override
    public void sendSimpleMail(EmailDetails details)
    {

        try {

            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());

            javaMailSender.send(mailMessage);

            System.out.println("mail sending successfully");
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            throw new EmailServiceErrorException(e.getMessage());
        }


    }

}
