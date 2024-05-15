package viewmodel;

import model.Model;
import model.Seat;
import model.User;

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
