package com.backend.taskagile.event;

import com.backend.taskagile.model.User;
import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {

  private User user;
  public EmailEvent(Object source,User user) {
    super(source);
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
