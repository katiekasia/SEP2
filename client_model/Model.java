package client_model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void bookTicket(Ticket ticket, User user);
  public void logIn(User user);
  public void cancelTicket(Ticket ticket);
  public void connect();
  public void disconnect();
  public int getPort();
  public void setPort(int port);
  public String getHost();
  
  public void reserveSeats(Seat[] seats,User customer,ScreeningDay day, Screening screening );

  public void reserveSeat(Seat seat, User customer,Screening screening);

  }
