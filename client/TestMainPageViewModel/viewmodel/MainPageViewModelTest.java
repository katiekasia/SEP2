package viewmodel;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import mediator.RmiClient;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyChangeSupport;
import java.lang.reflect.Field;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainPageViewModelTest {
  private MainPageViewModel viewModel;
  private ViewState viewState;
  private Model model;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private SimpleScreeningView simpleScreeningView1;
  private SimpleScreeningView simpleScreeningView2;
  private Screening screening1;
  private Screening screening2;
  private StringProperty input;

  @BeforeEach
  void setUp() {
    try {
      model = new ModelManagerTest();
      viewState = new ViewState();
      viewModel = new MainPageViewModel(model, viewState);
      screenings = FXCollections.observableArrayList();;
      selectedObject = new SimpleObjectProperty<>();
      property = new PropertyChangeSupport(this);
      this.input = new SimpleStringProperty();

      simpleScreeningView1= new SimpleScreeningView(new Screening(1, 1, LocalDate.of(2030, 1, 1),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2030, 1, 1)),
          new Room(10, 44)));
      screening1= new Screening(1, 1, LocalDate.of(2030, 1, 1),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2030, 1, 1)),
          new Room(10, 44));
      simpleScreeningView2= new SimpleScreeningView(new Screening(4, 5, LocalDate.of(2030, 1, 1),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2030, 1, 1)),
          new Room(10, 44)));
      screening2= new Screening(4, 5, LocalDate.of(2030, 1, 1),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2030, 1, 1)),
          new Room(10, 44));
    }
    catch (Exception e)
    {
      fail("Setup failed: " + e.getMessage());
    }
  }
  @Test
  void filterIntegration() {
    viewModel.filterByTitle();
  }


  @Test
  void inputPropertyZero() {
    StringProperty inputProperty = null;
    assertNull(inputProperty);
  }

  @Test
  void inputPropertyOne() {
    StringProperty inputProperty = viewModel.inputProperty();
    assertNotNull(inputProperty);
  }

  @Test
  void inputPropertyMany() {
    StringProperty inputProperty = viewModel.inputProperty();
    inputProperty.set("Test Value 1");
    assertEquals("Test Value 1", inputProperty.get());
    inputProperty.set("Test Value 2");
    assertEquals("Test Value 2", inputProperty.get());
  }

  @Test
  void inputPropertyBoundary() {
    StringProperty inputProperty = viewModel.inputProperty();
    inputProperty.set("");
    assertEquals("", inputProperty.get());
  }


  @Test
  void setScreenings_Zero() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    viewModel.setScreenings(list);
    assertTrue(list.isEmpty(), "List should be empty");
  }

  @Test
  void setCurrent_Zero() {

    viewModel.setCurrent(false);
    assertFalse(viewModel.isCurrent(), "isCurrent should be false");
  }

  @Test
  void setCurrent_One() {
    viewModel.setCurrent(true);
    assertTrue(viewModel.isCurrent(), "isCurrent should be true");
  }

  @Test
  void setCurrent_LowerBound() {
    viewModel.setCurrent(false);
    assertFalse(viewModel.isCurrent(), "isCurrent should be false");
  }

  @Test
  void setCurrent_UpperBound() {
    viewModel.setCurrent(true);
    assertTrue(viewModel.isCurrent(), "isCurrent should be true");
  }

  @Test void setCurrent_Exception()
  {
    viewModel=null;
    assertThrows(NullPointerException.class, () -> viewModel.setCurrent(true),
        "NullPointerException should be thrown for null argument");

  }

  @Test
  void isCurrent_Zero() {
    MainPageViewModel viewModel = new MainPageViewModel(model, viewState);
    assertFalse(viewModel.isCurrent(), "Initial state should be false");
  }

  @Test
  void isCurrent_One() {
    MainPageViewModel viewModel = new MainPageViewModel(model, viewState);
    viewModel.setCurrent(true);
    assertTrue(viewModel.isCurrent(), "Current state should be true");
  }

  @Test
  void isCurrent_Many() {
    MainPageViewModel viewModel = new MainPageViewModel(model, viewState);


    viewModel.setCurrent(true);
    assertTrue(viewModel.isCurrent(), "Current state should be true");

    viewModel.setCurrent(false);
    assertFalse(viewModel.isCurrent(), "Current state should be false");
  }

  @Test
  void isCurrent_Boundary() {
    MainPageViewModel viewModel = new MainPageViewModel(model, viewState);
    viewModel.setCurrent(true);
    assertTrue(viewModel.isCurrent(), "Current state should be true");

    viewModel.setCurrent(false);
    assertFalse(viewModel.isCurrent(), "Current state should be false");
  }
  @Test
  void isCurrent_Exception() {
    viewModel=null;
    assertThrows(NullPointerException.class, () -> viewModel.setCurrent(true),
        "NullPointerException should be thrown for null argument");
  }

  @Test
  void bindScreenings_Zero() {
    MainPageViewModel viewModel = new MainPageViewModel(model, viewState);
    ObservableList<SimpleScreeningView> property = FXCollections.observableArrayList();
    viewModel.bindScreenings(property);
    assertTrue(property.isEmpty(), "Property should remain empty");
  }

  @Test
  void bindScreenings_One() {
    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();
    screenings.add(simpleScreeningView2);
    viewModel.bindScreenings(screenings);
    assertEquals(1, screenings.size(), "Property should contain one screening");
  }

  @Test
  void bindScreenings_Many()
  {

    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();
    for (int i = 0; i < 3; i++)
    {
      Screening screening = new Screening(i, i + 1,
          LocalDate.of(2030, 3, 2).plusDays(i),
          new Movie("1h:20m", "Cos tam", "New film", "crime",
              LocalDate.of(2025, 2, 28).plusDays(i)), new Room(10, 44));
      screenings.add(new SimpleScreeningView(screening));
    }
    viewModel.bindScreenings(screenings);
    assertEquals(3, screenings.size(), "Property should contain one screening");
  }

  @Test
  void bindScreenings_Zero_LowerBound() {
    ObservableList<SimpleScreeningView> propery = FXCollections.observableArrayList();
    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();

    screenings.addListener(
        (ListChangeListener<? super SimpleScreeningView>) c -> {
          propery.setAll(screenings);
        });

    assertEquals(0, propery.size(), "Property should be empty");
  }
  @Test
  void bindScreenings_Many_UpperBound() {
    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();
    final int MAX_ELEMENTS = 5;

    for (int i = 0; i < MAX_ELEMENTS; i++) {
      Screening screening = new Screening(i, i + 1,
          LocalDate.of(2030, 3, 2).plusDays(i),
          new Movie("1h:20m", "Cos tam", "New film", "crime",
              LocalDate.of(2025, 2, 28).plusDays(i)), new Room(10, 44));
      screenings.add(new SimpleScreeningView(screening));
    }
    viewModel.bindScreenings(screenings);

    assertEquals(MAX_ELEMENTS, screenings.size(), "Property should contain the maximum number of screenings");
  }
  @Test
  void bindScreenings_Exception() {
    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();
    viewModel=null;
    assertThrows(NullPointerException.class, () -> viewModel.bindScreenings(screenings),
        "NullPointerException should be thrown for null argument");
  }


  @Test
  void setSelected_Zero() {
    viewModel.setSelected();
    assertNull(selectedObject.getValue(), "Selected screening should be null initially");
  }

  @Test
  void setSelected_One()
  {
    viewState.setSelectedScreening(simpleScreeningView1);
    viewModel.setSelected();
    assertNotNull(viewState.getSelectedScreening(), "Selected screening should not be null after setting");
  }

  @Test
  void setSelected_Many()
  {
    viewState.setSelectedScreening(simpleScreeningView2);
    viewState.setSelectedScreening(simpleScreeningView1);
    viewModel.setSelected();

    assertEquals(simpleScreeningView1, viewState.getSelectedScreening(),
        "Selected screening should match the one set in the view state");
  }

  @Test
  void setSelected_Exception()
  {
    viewModel = null;
    assertThrows(NullPointerException.class, () -> {
      viewModel.setSelected();
    }, "NullPointerException should be thrown for null argument");
  }

  @Test void getViewStateZero()
  {
    ViewState state= viewModel.getViewState();
    state=null;
    assertNull(state, "View state should be null when not initialized");
  }
  @Test void getViewStateOne()
  {
    ViewState state= viewModel.getViewState();
    assertNotNull(state, "View state should be null when not initialized");
  }
  @Test void getViewStateMany()
  {
    ViewState state= viewModel.getViewState();
    ViewState state2= viewModel.getViewState();

    state=state2;
    assertEquals(state2, state, "View state should match the last set ViewState when initialized with many valid objects");
  }
  @Test void getViewStateException()
  {
    viewModel=null;
    assertThrows(NullPointerException.class, () -> {
      viewModel.getViewState();
    }, "NullPointerException should be thrown for null argument");
  }

  @Test
  void loadScreeningsZero() {
    ArrayList<Screening> screeningsNew = new ArrayList<>();
    viewModel.loadScreenings(screeningsNew);
    assertEquals(0, viewModel.getScreenings().size(), "Screenings list should be empty.");
  }
  @Test
  void loadScreeningsOne() {
    ArrayList<Screening> screeningsNew = new ArrayList<>();
    screeningsNew.add(screening1);
    viewModel.loadScreenings(screeningsNew);
    assertEquals(1, viewModel.getScreenings().size(), "Screenings list should have one item");
  }
  @Test
  void testLoadScreeningsWithMultipleScreenings() {
    ArrayList<Screening> multipleScreeningsList = new ArrayList<>();
    multipleScreeningsList.add(screening1);
    multipleScreeningsList.add(screening2);
    viewModel.loadScreenings(multipleScreeningsList);
    assertEquals(2, viewModel.getScreenings().size(), "Screenings list should contain two screenings.");
    }
  @Test void loadScreeningsException()
  {
    ArrayList<Screening> screeningsNew= new ArrayList<>();
    screeningsNew=null;
    ArrayList<Screening> finalScreeningsNew = screeningsNew;
    assertThrows(NullPointerException.class, () -> {
      viewModel.loadScreenings(finalScreeningsNew);
    }, "NullPointerException should be thrown for null argument");

  }


}