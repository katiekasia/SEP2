package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import viewmodel.TransitionPageViewModel;
import viewmodel.ViewState;

import java.util.function.UnaryOperator;

public class TransitionPageViewController
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
  @FXML private Button fidelityPoints;
  @FXML private Button manage;
  @FXML private Button signOut;
  @FXML private Button ticketConfirmation;
  @FXML private Button backToMovieSelection;
  @FXML private Button goToSeatSelection;
  @FXML private Label totalPrice;
  public void init(ViewHandler viewHandler, TransitionPageViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    this.viewState = viewModel.getViewState();
    movieTitle.setText(viewState.getSelectedScreening().movieProperty().get());
    length.setText(String.valueOf(viewState.getSelectedScreening().lengthProperty().get()));
    movieGenre.setText(viewState.getSelectedScreening().genreProperty().get());
    movieDate.setText(viewState.getSelectedScreening().dateProperty().get());
    movieTime.setText(viewState.getSelectedScreening().timeProperty().get());
    roomID.setText(String.valueOf(viewState.getSelectedScreening().roomProperty().get()));

    initializeTicketInputFields();
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

    numberOfStandartTickets.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());
    numberOfVIPTickets.textProperty().addListener((obs, oldVal, newVal) -> updateTotal());
  }

  private void updateTotal() {
    int standardTickets = parseTicketNumber(numberOfStandartTickets.getText());
    int vipTickets = parseTicketNumber(numberOfVIPTickets.getText());
    int total = standardTickets + vipTickets;

    if (total <= 44) {
      viewState.setNumberOfStandardTickets(standardTickets);
      viewState.setNumberOfVIPTickets(vipTickets);
      int totalPriceAmount = standardTickets * 120 + vipTickets * 170;
      totalPrice.setText(String.valueOf(totalPriceAmount) + " DKK");
    } else {
      System.out.println("Total tickets cannot exceed 44. Please adjust your quantities.");
      totalPrice.setText("Limit exceeded!");
    }
  }

  @FXML public void onManage()
  {
    viewHandler.openView("managePage");
  }
  @FXML public void onFidelityPoints()
  {
    //
  }


  @FXML public void onSignOut()
  {

  }

  private int parseTicketNumber(String text) {
    try {
      return Integer.parseInt(text);
    } catch (NumberFormatException e) {
      return 0;
    }
  }
  @FXML private void onBackToMovieSelection()
  {
    viewHandler.openView("mainPage");
  }

  private boolean isValidTicketEntry() {
    int standardTickets = parseTicketNumber(numberOfStandartTickets.getText());
    int vipTickets = parseTicketNumber(numberOfVIPTickets.getText());
    int totalTickets = standardTickets + vipTickets;

    return totalTickets > 0 && totalTickets <= 44;
  }

  @FXML public void onBackToSeatSelection() {
    if (isValidTicketEntry()) {
      viewHandler.openView("seatMapping");
    } else {
      System.out.println("Please enter valid ticket numbers before proceeding.");
    }
  }

}
