import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

public interface Model extends UnnamedPropertyChangeSubject
{
  public void bookTicket(Ticket ticket, Customer customer);
  public void logIn(Customer customer);
  public void cancelTicket(Ticket ticket);
}
