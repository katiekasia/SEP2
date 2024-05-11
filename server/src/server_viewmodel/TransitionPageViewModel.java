package server_viewmodel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import server_model.Model;
import server_model.Screening;
import server_model.Seat;
import server_model.User;

import javax.swing.text.View;

public class TransitionPageViewModel
{
  private Model model;
  private ViewState viewState;

//  private StringProperty movieTitle;
//  private IntegerProperty length;
//  private StringProperty movieGenre;
//  private StringProperty movieDate;
//  private StringProperty movieTime;
//  private IntegerProperty roomID;


  public TransitionPageViewModel(Model model, ViewState viewState) {
    this.model = model;
    this.viewState = viewState;


//    movieTitle = viewState.getSelectedScreening().movieProperty();
//    length = viewState.getSelectedScreening().lengthProperty();
//    movieGenre = viewState.getSelectedScreening().genreProperty();
//    movieDate = viewState.getSelectedScreening().dateProperty();
//    movieTime = viewState.getSelectedScreening().timeProperty();
//    roomID = viewState.getSelectedScreening().roomProperty();
  }

  public void bookTicket(Seat seat, User user) {
    try {
      model.reserveSeat(seat, user, viewState.getSelectedScreening()
          .getScreening());
    } catch (Exception e) {

    }
  }
  public ViewState getViewState()
  {
    return viewState;
  }
}
