package com.backend.taskagile.event;

import com.backend.taskagile.model.User;

public class EmailEvent {

  private User user;
  public EmailEvent(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
