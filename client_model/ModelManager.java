package client_model;

import client_model.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ModelManager implements Model
{
  private PropertyChangeSupport propertyChangeSupport;

  @Override public void addListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);

  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    propertyChangeSupport.removePropertyChangeListener(listener);
  }

  @Override public void bookTicket(Ticket ticket, User customer)
  {

  }

  @Override public void logIn(User customer)
  {

  }

  @Override public void cancelTicket(Ticket ticket)
  {

  }
}