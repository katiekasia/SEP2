package view;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Region;
import viewmodel.SeatMappingViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class SeatMappingViewController implements PropertyChangeListener
    {
      private Region root;
      private SeatMappingViewModel viewModel;
      private ViewHandler viewHandler;


      @FXML private Button back;
      @FXML private Button confirm;
      @FXML private CheckBox a1;
      @FXML private CheckBox a2;
      @FXML private CheckBox a3;
      @FXML private CheckBox a4;
      @FXML private CheckBox a5;
      @FXML private CheckBox a6;
      @FXML private CheckBox a7;
      @FXML private CheckBox a8;
      @FXML private CheckBox a9;
      @FXML private CheckBox a10;
      @FXML private CheckBox a11;
      @FXML private CheckBox b1;
      @FXML private CheckBox b2;
      @FXML private CheckBox b3;
      @FXML private CheckBox b4;
      @FXML private CheckBox b5;
      @FXML private CheckBox b6;
      @FXML private CheckBox b7;
      @FXML private CheckBox b8;
      @FXML private CheckBox b9;
      @FXML private CheckBox b10;
      @FXML private CheckBox b11;
      @FXML private CheckBox c1;
      @FXML private CheckBox c2;
      @FXML private CheckBox c3;
      @FXML private CheckBox c4;
      @FXML private CheckBox c5;
      @FXML private CheckBox c6;
      @FXML private CheckBox c7;
      @FXML private CheckBox c8;
      @FXML private CheckBox c9;
      @FXML private CheckBox c10;
      @FXML private CheckBox c11;
      @FXML private CheckBox d1;
      @FXML private CheckBox d2;
      @FXML private CheckBox d3;
      @FXML private CheckBox d4;
      @FXML private CheckBox d5;
      @FXML private CheckBox d6;
      @FXML private CheckBox d7;
      @FXML private CheckBox d8;
      @FXML private CheckBox d9;
      @FXML private CheckBox d10;
      @FXML private CheckBox d11;
      private ArrayList<CheckBox> checkBoxes;
      public void init(ViewHandler viewHandler, SeatMappingViewModel viewModel, Region root)
      {
        this.viewHandler = viewHandler;
        this.viewModel = viewModel;
        this.viewModel.addListener(this);
        this.root = root;
        checkBoxes = new ArrayList<>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
          if (field.getType().equals(CheckBox.class)) {
            field.setAccessible(true);
            try {
              CheckBox checkBox = (CheckBox) field.get(this);
              if (checkBox != null) {
                checkBoxes.add(checkBox);
              }
            } catch (IllegalAccessException e) {
              Alert alert = new Alert(Alert.AlertType.ERROR);
              alert.setHeaderText(e.getMessage());
              alert.showAndWait();
            }
          }
        }

    a1.setOnAction(e -> handleSeatSelection(a1, "A1"));
    a2.setOnAction(e -> handleSeatSelection(a2, "A2"));
    a3.setOnAction(e -> handleSeatSelection(a3, "A3"));
    a4.setOnAction(e -> handleSeatSelection(a4, "A4"));
    a5.setOnAction(e -> handleSeatSelection(a5, "A5"));
    a6.setOnAction(e -> handleSeatSelection(a6, "A6"));
    a7.setOnAction(e -> handleSeatSelection(a7, "A7"));
    a8.setOnAction(e -> handleSeatSelection(a8, "A8"));
    a9.setOnAction(e -> handleSeatSelection(a9, "A9"));
    a10.setOnAction(e -> handleSeatSelection(a10, "A10"));
    a11.setOnAction(e -> handleSeatSelection(a11, "A11"));

    // Setup for B1 to B11
    b1.setOnAction(e -> handleSeatSelection(b1, "B1"));
    b2.setOnAction(e -> handleSeatSelection(b2, "B2"));
    b3.setOnAction(e -> handleSeatSelection(b3, "B3"));
    b4.setOnAction(e -> handleSeatSelection(b4, "B4"));
    b5.setOnAction(e -> handleSeatSelection(b5, "B5"));
    b6.setOnAction(e -> handleSeatSelection(b6, "B6"));
    b7.setOnAction(e -> handleSeatSelection(b7, "B7"));
    b8.setOnAction(e -> handleSeatSelection(b8, "B8"));
    b9.setOnAction(e -> handleSeatSelection(b9, "B9"));
    b10.setOnAction(e -> handleSeatSelection(b10, "B10"));
    b11.setOnAction(e -> handleSeatSelection(b11, "B11"));

    // Setup for C1 to C11
    c1.setOnAction(e -> handleSeatSelection(c1, "C1"));
    c2.setOnAction(e -> handleSeatSelection(c2, "C2"));
    c3.setOnAction(e -> handleSeatSelection(c3, "C3"));
    c4.setOnAction(e -> handleSeatSelection(c4, "C4"));
    c5.setOnAction(e -> handleSeatSelection(c5, "C5"));
    c6.setOnAction(e -> handleSeatSelection(c6, "C6"));
    c7.setOnAction(e -> handleSeatSelection(c7, "C7"));
    c8.setOnAction(e -> handleSeatSelection(c8, "C8"));
    c9.setOnAction(e -> handleSeatSelection(c9, "C9"));
    c10.setOnAction(e -> handleSeatSelection(c10, "C10"));
    c11.setOnAction(e -> handleSeatSelection(c11, "C11"));

    // Setup for D1 to D11
    d1.setOnAction(e -> handleSeatSelection(d1, "D1"));
    d2.setOnAction(e -> handleSeatSelection(d2, "D2"));
    d3.setOnAction(e -> handleSeatSelection(d3, "D3"));
    d4.setOnAction(e -> handleSeatSelection(d4, "D4"));
    d5.setOnAction(e -> handleSeatSelection(d5, "D5"));
    d6.setOnAction(e -> handleSeatSelection(d6, "D6"));
    d7.setOnAction(e -> handleSeatSelection(d7, "D7"));
    d8.setOnAction(e -> handleSeatSelection(d8, "D8"));
    d9.setOnAction(e -> handleSeatSelection(d9, "D9"));
    d10.setOnAction(e -> handleSeatSelection(d10, "D10"));
    d11.setOnAction(e -> handleSeatSelection(d11, "D11"));

    setSeatsFromModel();
        viewModel.reset();
        viewModel.addListener(this);

  }

  private void setSeatsFromModel(){

        for (CheckBox checkBox : checkBoxes){
          if (viewModel.setSeats().contains(checkBox.getId().toUpperCase())){
            checkBox.setDisable(true);
            if (checkBox.isSelected()){
              checkBox.setSelected(false);
              viewModel.deselectSeat(checkBox.getId());
            }
          }
        }
  }
  private void selectionControl(){
        if (!viewModel.keepSelecting())
        {
          for (CheckBox checkBox : checkBoxes)
          {
            if (!checkBox.isSelected())
            {
              checkBox.setDisable(true);
            }
          }
        }
        if (viewModel.keepSelecting()){
          for (CheckBox checkBox : checkBoxes){
            if (checkBox.isDisabled()){
              checkBox.setDisable(false);
            }
          }
          setSeatsFromModel();
        }
  }
  private void handleSeatSelection(CheckBox checkBox, String seatId)
  {
    if (checkBox.isSelected())
    {
      viewModel.selectSeat(seatId);
      selectionControl();
    }
    else
    {
      viewModel.deselectSeat(seatId);
      selectionControl();
    }
  }
  @FXML public void onConfirm()
  {
    if (viewModel.proceedPressed()){
    viewHandler.openView("ticketConfirmation");}
  }
  @FXML public void onBack()
  {
    viewHandler.openView("transitionPage");
  }

      @Override public void propertyChange(PropertyChangeEvent evt)
      {
        Platform.runLater(() ->{
        if (evt.getPropertyName().equals("reset")){
          setSeatsFromModel();
        } else if (evt.getPropertyName().equals("fatalError")){
          viewHandler.close();
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("A fatal error has occured: " + evt.getNewValue());
          alert.showAndWait();}});
      }
  }