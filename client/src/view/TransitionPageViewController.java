package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.TransitionPageViewModel;
import viewmodel.ViewState;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.function.UnaryOperator;

public class TransitionPageViewController implements PropertyChangeListener
{

  private Region root;
  private TransitionPageViewModel viewModel;
  private ViewHandler viewHandler;
  private ViewState viewState;
  @FXML private TextField numberOfStandartTickets;
  @FXML private TextField numberOfVIPTickets;

  @FXML private Label username;
  @FXML private Label movieTitle;
  @FXML private Label length;
  @FXML private Label movieGenre;
  @FXML private Label movieDate;
  @FXML private Label movieTime;
  @FXML private Label roomID;
  @FXML private Label releaseDate;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button backToMovieSelection;
  @FXML private Button goToSeatSelection;
  @FXML private Label totalPrice;
  @FXML private Label standardPrice;
  @FXML private Label vipPrice;
  public void init(ViewHandler viewHandler, TransitionPageViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();

    this.viewModel.addListener(this);
    this.viewModel.setCurrent(true);
    standardPrice.textProperty().bind(viewModel.standardPriceProperty());
    vipPrice.textProperty().bind(viewModel.vipPriceProperty());
    this.viewModel.bindStandard(numberOfStandartTickets.textProperty());
    this.viewModel.bindVIP(numberOfVIPTickets.textProperty());
    this.viewModel.bindPrice(totalPrice.textProperty());

    movieTitle.textProperty().bind(viewModel.movieTitleProperty());

    length.textProperty().bind(viewModel.lengthProperty());

    movieGenre.textProperty().bind(viewModel.movieGenreProperty());

    movieDate.textProperty().bind(viewModel.movieDateProperty());

    movieTime.textProperty().bind(viewModel.movieTimeProperty());

    roomID.textProperty().bind(viewModel.roomIDProperty().asString());

    releaseDate.textProperty().bind(viewModel.getReleaseDate());


    username.textProperty().bind(viewState.nameProperty());

    initializeTicketInputFields();
    viewModel.reset();
  }
  private void initializeTicketInputFields() {
    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
      String newText = change.getControlNewText();
      if (newText.matches("([1-9]|[1-3][0-9]|4[0-4]|0)?")) {
        return change;
      }
      return null;
    };

    numberOfStandartTickets.setTextFormatter(new TextFormatter<>(integerFilter));
    numberOfVIPTickets.setTextFormatter(new TextFormatter<>(integerFilter));

    numberOfStandartTickets.textProperty().addListener((obs, oldVal, newVal) -> viewModel.updateTotal());
    numberOfVIPTickets.textProperty().addListener((obs, oldVal, newVal) -> viewModel.updateTotal());
  }

  @FXML public void onManage()
  {
    viewModel.setCurrent(false);
    viewHandler.openView("managePage");
  }



  @FXML public void onSignOut()
  {
    viewModel.setCurrent(false);
    viewState.logOut();
    viewHandler.openView("login");
  }

  @FXML public void onOrderConfirmation(){
    viewModel.setCurrent(false);
    viewHandler.openView("orderConfirmation");
  }

  @FXML private void onBackToMovieSelection()
  {
    viewModel.setCurrent(false);
    viewState.setSelectedScreening(null);
    viewHandler.openView("mainPage");
  }



  @FXML public void onBackToSeatSelection() {
    if (viewModel.isValidTicketEntry()) {
      viewModel.setCurrent(false);
      viewHandler.openView("seatMapping");
    } else {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setHeaderText("Please enter valid ticket numbers before proceeding.");
      alert.showAndWait();
    }
  }
  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() ->{
      if (evt.getPropertyName().equals("fatalError")){
        viewHandler.close();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("A fatal error has occured: " + evt.getNewValue());
        alert.showAndWait();
      }});
  }
}
