package com.backend.taskagile.event;

import com.backend.taskagile.mail.EmailSender;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailEventListener {

  private final EmailSender emailSender;

  @EventListener
  public void handleEvent(EmailEvent event) {
    emailSender.send(event.getUser().getEmail(),
        buildEmail(event.getUser().getFirstName()));
  }

  private String buildEmail(String name) {
    return "<div>\n"
        + "    <h2 style=\"font-family: Impact, Haettenschweiler, 'Arial Narrow Bold', sans-serif;\n"
        + "    text-align: center;\">TaskAgile</h2>\n"
        + "    <p>Welcome " + name
        + ". You have already successfully registered on the TaskAgile platform. Make the most of all the resources that the application offers you. See you there.</p>\n"
        + "</div>\n"
        + "<footer>\n"
        + "    <small>TaskAgile Corporation © 2021</small>\n"
        + "</footer>";
  }
}
