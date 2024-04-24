package client_model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void logIn(User user);
  public void connect();
  public void disconnect();
  public int getPort();
  public void setPort(int port);
  public String getHost();
  public void reserveSeats(Seat[] seats,User customer,ScreeningDay day, Screening screening );

  public void reserveSeat(Seat seat, User customer,Screening screening);
  public boolean checkSeatAvailability(int index, Screening screening);
  boolean checkSeatAvailability(int index, Screening screening);
  public Seat[] getAvailableSeats(Screening screening);
  public boolean holdSeat(Seat seat, User customer, Screening screening);
  public void releaseHoldSeat(Seat seat, User customer, Screening screening);

  public double calculateTotalPrice(Seat[] seats, Screening screening);
  public void updateSeatStatus(Seat seat);

  }
