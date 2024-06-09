package viewmodel;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import model.Model;
import model.User;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ManageViewModel implements PropertyChangeListener,
    UnnamedPropertyChangeSubject
{
  /**
   * A 2 argument constructor for manageviewmodel.
   * @param Model model
   * @param Viewstate
   *
   */
  private Model model;

  private ViewState viewState;
  private PropertyChangeSupport property;
  private boolean isCurrent;


  public ManageViewModel(Model model, ViewState viewState)
  {
    this.model= model;
    this.viewState= viewState;
    property = new PropertyChangeSupport(this);
    this.model.addListener(this);
    isCurrent = false;
  }
  /** method returning username as String**/
  public String getUsername()

  {
    return viewState.getUser().getUsername();
  }
  /** method setting isCurrent boolean**/
  public void setCurrent(boolean current)
  {
    isCurrent = current;
  }
  /** method responsible for deleting the account held in the ViewState(one currently logged in**/
  public void deleteAccount()
  {
    String username = viewState.getUser().getUsername();
    model.deleteAccount(username);
  }
  /** method responsible for updating the account held in the ViewState(one currently logged in**/
  public void updateUserInDatabase(User user, String previousUsername)
  {
    User newUser =viewState.getUser();
    try
    {
      model.updateUser(user, previousUsername);
    }catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText(e.getMessage());
      alert.showAndWait();
    }

  }
  /** method returning password as String
   * @return password as String **/
  public String getPassword()
  {
    return viewState.getUser().getPassword();
  }
  /** method returning phone number as String
   * @return phonenumber as String**/
  public String getPhoneNumber()
  {
    return viewState.getUser().getPhoneNumber();
  }
  /** method returning name  as String
   * @return name as String**/
  public String getName()
  {
    return viewState.getUser().getFstName();
  }
  /** method returning last name  as String
   * @return last name as String****/
  public String getSurname()
  {
    return viewState.getUser().getLstName();
  }  /** method returning ViewState**/
  public ViewState getViewState()
  {
    return viewState;
  }
  /** method returning email  as String
   * @return email as String****/
  public String getEmail()
  {
    return viewState.getUser().getEmail();
  }
  /**
   * Responds to property change events.
   *
   * @param evt The property change event to be handled.
   */
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError") && isCurrent){
        property.firePropertyChange(evt.getPropertyName(),null,evt.getNewValue());
      }});
  }
  /**
   * Assigns listener to the property.
   * @param listener the listener to be added
   */
  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }
  /**
   * Removes listener from the property.
   * @param listener the listener to be added
   */
  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
