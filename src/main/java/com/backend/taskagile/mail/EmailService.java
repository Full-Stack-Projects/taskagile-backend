package com.backend.taskagile.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

  private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

  private final JavaMailSender javaMailSender;

  @Override
  @Async
  public void send(String to, String mail) {
    try {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

      helper.setText(mail, true);
      helper.setTo(to);
      helper.setSubject("Welcome to TaskAgile");
      helper.setFrom("gerfriend1@gmail.com");
      javaMailSender.send(mimeMessage);
    } catch (MessagingException e) {
      LOGGER.error("Failed to send email", e);
      throw new IllegalStateException("Failed to send email");
    }
  }
}
