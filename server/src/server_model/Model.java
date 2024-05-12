package server_model;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.util.ArrayList;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void logIn(User user);
  public void connect();
  public void disconnect();
  public int getPort();
  public void setPort(int port);
  public String getUsername();
  public String getHost();
  public void reserveSeats(Seat[] seats, User customer,
       Screening screening );
  public boolean checkSeatAvailability(int index, Screening screening);

  public void reserveSeat(Seat seat, User customer,
      Screening screening);
  public Seat[] getAvailableSeats(Screening screening);
  public Seat[] getEmptySeats(Screening screening);

  public double calculateTotalPrice();
  public void updateSeatToBooked(Seat seat, Ticket ticket);

  public void addOrder(Order order);
  void logIn(String username, String password);
  void register(String username, String password, String email, String firstName, String lastName, String phone);
void addScreening(Screening screening);
void removeScreening(Screening screening);
void removeByDate(SimpleDate date);
ArrayList<Screening> getAllScreenings();
int getNbOfScreenings();
Screening getScreening(Screening screening);
  public ArrayList<Ticket> getAllTickets();
  public User getUser();
  Screening findScreeningBySeatId(String seatId);
/*
 public Ticket getTicketBySeat(Seat seat);
  public Seat getSeatByScreening(Screening screening);

 */
}
