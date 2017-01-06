package com.cyb.jaas;

import java.security.Principal;

public class User
  implements Principal
{
  private String userName;

  public User(String userName)
  {
    this.userName = userName;
  }

  public String getName() {
    return this.userName;
  }
}