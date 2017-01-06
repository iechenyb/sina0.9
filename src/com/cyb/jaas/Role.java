package com.cyb.jaas;

import java.security.Principal;

public class Role
  implements Principal
{
  private String role;

  public Role(String role)
  {
    this.role = role;
  }

  public String getName() {
    return this.role;
  }

  public boolean equals(Object object) {
    boolean flag = false;
    if (object == null)
      flag = false;
    if (this == object)
      flag = true;
    if (!(object instanceof Role))
      flag = false;
    if (object instanceof Role) {
      Role that = (Role)object;
      if (getName().equals(that.getName()))
        flag = true;
    }

    System.out.println("flag=" + flag);
    return flag;
  }
}