package server_view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import server_viewmodel.TransitionPageViewModel;
import server_viewmodel.ViewState;

public class TransitionPageViewController
{

    private Region root;
    private TransitionPageViewModel viewModel;
    private ViewHandler viewHandler;
    private ViewState viewState;

  @FXML private Label username;
  @FXML private Label movieTitle;
  @FXML private Label length;
  @FXML private Label movieGenre;
  @FXML private Label movieDate;
  @FXML private Label movieTime;
  @FXML private Label roomID;
  @FXML private ChoiceBox<Integer> numberOfStandartTickets;
  @FXML private ChoiceBox<Integer> numberOfVIPTickets;
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

      initializeTicketChoiceBoxes();
    }
  private void initializeTicketChoiceBoxes() {
    numberOfStandartTickets.getItems().addAll(0, 1, 2, 3, 4, 5); // or any other logic
    numberOfVIPTickets.getItems().addAll(0, 1, 2, 3, 4, 5); // or any other logic

    // Add listeners to update the total price
    numberOfStandartTickets.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateTotal());
    numberOfVIPTickets.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> updateTotal());
  }

  private void updateTotal() {
    int standardTickets = numberOfStandartTickets.getValue() != null ? numberOfStandartTickets.getValue() : 0;
    int vipTickets = numberOfVIPTickets.getValue() != null ? numberOfVIPTickets.getValue() : 0;

    int total = standardTickets * 120 + vipTickets * 170;
    totalPrice.setText(String.valueOf(total) + " DKK");
  }
  @FXML public void onManage()
  {

  }
  @FXML public void onFidelityPoints()
  {
    //
  }
  @FXML public void onTicketConfirmation()
  {

    viewHandler.openView("ticketConfirmation");
  }
  @FXML public void onSignOut()
  {

  }
  @FXML public void onBackToMovieSelection()
  {
    viewHandler.openView("movieSelection");
  }
  @FXML public void onBackToSeatSelection()
  {

  }



}
