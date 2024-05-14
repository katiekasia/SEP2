package model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void bookTicket(Ticket ticket, User user);
  public void logIn(User user);
  public void cancelTicket(Ticket ticket);
}
