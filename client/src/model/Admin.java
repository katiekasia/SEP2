package model;

public class Admin
{
  private static final String USERNAME = "Admin";
  private String password;

  public Admin(String password){
    this.password = password;
  }

  public String getPassword()
  {
    return password;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public static String getUSERNAME()
  {
    return USERNAME;
  }
}
