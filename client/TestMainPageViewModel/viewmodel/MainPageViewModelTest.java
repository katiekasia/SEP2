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
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainPageViewModelTest {
  private MainPageViewModel viewModel;
  private RmiClient client;
  private ViewState viewState;
  private Model model;
  private PropertyChangeSupport property;
  private ObservableList<SimpleScreeningView> screenings;
  private ObjectProperty<SimpleScreeningView> selectedObject;
  private SimpleScreeningView simpleScreeningView1;
  private SimpleScreeningView simpleScreeningView2;
  private Screening screening1;
  private Screening screening2;

  @BeforeEach
  void setUp() {
    try {
      client = new RmiClient("localhost");
      model = new ModelManager(client);
      viewState = new ViewState();
      viewModel = new MainPageViewModel(model, viewState);
      screenings = FXCollections.observableArrayList();
      selectedObject = new SimpleObjectProperty<>();
      property = new PropertyChangeSupport(this);

      simpleScreeningView1= new SimpleScreeningView(new Screening(1, 1, LocalDate.of(2030, 3, 2),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2025, 2, 28)),
          new Room(10, 44)));
      screening1= new Screening(1, 1, LocalDate.of(2030, 3, 2),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2025, 2, 28)),
          new Room(10, 44));
      simpleScreeningView2= new SimpleScreeningView(new Screening(4, 5, LocalDate.of(2030, 3, 2),
          new Movie("1h:20m", "Cos tam", "Idk", "crime", LocalDate.of(2025, 2, 28)),
          new Room(10, 44)));
      screening2= new Screening(4, 5, LocalDate.of(2030, 3, 2),
          new Movie("1h:20m", "Cos tam", "Idk", "crime", LocalDate.of(2025, 2, 28)),
          new Room(10, 44));
    }
    catch (Exception e)
    {
      fail("Setup failed: " + e.getMessage());
    }
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
  void inputPropertyException() {
    StringProperty inputProperty = viewModel.inputProperty();
    assertThrows(NullPointerException.class, () -> {
      inputProperty.set(null);
    });
  }

  @Test
  void setScreenings_Zero() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    viewModel.setScreenings(list);
    assertTrue(list.isEmpty(), "List should be empty");
  }

  @Test
  void setScreenings_One() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    list.add(simpleScreeningView1);
    viewModel.setScreenings(list);
    assertEquals(1, list.size(), "List should contain one element");
  }

  @Test
  void setScreenings_Many() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    for (int i = 0; i < 10; i++) {
      Screening screening = new Screening(i, i + 1, LocalDate.of(2030, 3, 2).plusDays(i),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2025, 2, 28).plusDays(i)), new Room(10, 44));
      list.add(new SimpleScreeningView(screening));
    }
    viewModel.setScreenings(list);
    assertEquals(10, list.size(), "List should contain ten elements");
  }
  @Test
  void setScreenings_LowerBound() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    list.add(simpleScreeningView2);
    viewModel.setScreenings(list);
    assertEquals(1, list.size(), "List should contain one element");
  }

  @Test
  void setScreenings_UpperBound() {
    ObservableList<SimpleScreeningView> list = FXCollections.observableArrayList();
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      Screening screening = new Screening(i, i + 1, LocalDate.of(2030, 3, 2).plusDays(i),
          new Movie("1h:20m", "Cos tam", "New film", "crime", LocalDate.of(2025, 2, 28).plusDays(i)), new Room(10, 44));
      list.add(new SimpleScreeningView(screening));
    }
    viewModel.setScreenings(list);
    assertEquals(Integer.MAX_VALUE, list.size(), "List should contain Integer.MAX_VALUE elements");
  }

  @Test
  void setScreenings_Exception() {
    ObservableList<SimpleScreeningView> list = null;
    assertThrows(NullPointerException.class, () -> viewModel.setScreenings(list),
        "NullPointerException should be thrown for null argument");
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
    ObservableList<SimpleScreeningView> propery = FXCollections.observableArrayList();
    ObservableList<SimpleScreeningView> screenings = FXCollections.observableArrayList();
    final int MAX_ELEMENTS = 10;

    for (int i = 0; i < MAX_ELEMENTS; i++) {
      Screening screening = new Screening(i, i + 1,
          LocalDate.of(2030, 3, 2).plusDays(i),
          new Movie("1h:20m", "Cos tam", "New film", "crime",
              LocalDate.of(2025, 2, 28).plusDays(i)), new Room(10, 44));
      screenings.add(new SimpleScreeningView(screening));
    }

    screenings.addListener(
        (ListChangeListener<? super SimpleScreeningView>) c -> {
          propery.setAll(screenings);
        });

    assertEquals(MAX_ELEMENTS, propery.size(), "Property should contain the maximum number of screenings");
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
//
//  @Test
//  void loadScreeningsZero() {
//    ArrayList<Screening> screeningsNew = new ArrayList<>();
//    viewModel.loadScreenings(screeningsNew);
//    assertEquals(0, viewModel.getScreenings().size(), "Screenings list should be empty.");
//  }
//  @Test
//  void loadScreeningsOne() {
//    ArrayList<Screening> screeningsNew = new ArrayList<>();
//    screeningsNew.add(screening1);
//    viewModel.loadScreenings(screeningsNew);
//    assertEquals(1, viewModel.getScreenings().size(), "Screenings list should have one item");
//    assertEquals(screening1, viewModel.getScreenings().get(0), "The screening should match the one added");
//  }
//  @Test
//  void testLoadScreeningsWithMultipleScreenings() {
//    ArrayList<Screening> multipleScreeningsList = new ArrayList<>();
//    multipleScreeningsList.add(screening1);
//    multipleScreeningsList.add(screening2);
//    viewModel.loadScreenings(multipleScreeningsList);
//    assertEquals(2, screenings.size(), "Screenings list should contain two screenings.");
//    assertEquals(simpleScreeningView1, screenings.get(0), "First loaded screening does not match.");
//    assertEquals(simpleScreeningView2, screenings.get(1), "Second loaded screening does not match.");
//  }
//  @Test void loadScreeningsException()
//  {
//    ArrayList<Screening> screeningsNew= new ArrayList<>();
//    screeningsNew=null;
//    ArrayList<Screening> finalScreeningsNew = screeningsNew;
//    assertThrows(NullPointerException.class, () -> {
//      viewModel.loadScreenings(finalScreeningsNew);
//    }, "NullPointerException should be thrown for null argument");
//
//  }
@Test void onSearchZero()
{
  viewModel.onSearch(LocalDate.of(2050, 3, 2));
  assertTrue(viewModel.getScreenings().isEmpty(), "The screenings list should be empty.");

}

  @Test void onSearchMany()
  {
    viewModel.onSearch(LocalDate.of(2030, 3, 2));
    assertEquals(3, viewModel.getScreenings().size(), "The screenings list should contain three screenings.");
  }
  @Test
  void testOnSearchBoundary()
  {
    LocalDate earliestDate = LocalDate.of(2020, 1, 1);
    LocalDate latestDate = LocalDate.of(2040, 12, 31);
    viewModel.onSearch(earliestDate);
    assertFalse(viewModel.getScreenings().isEmpty(), "The screenings list should be empty for the earliest date.");

    viewModel.onSearch(latestDate);
    assertFalse(viewModel.getScreenings().isEmpty(), "The screenings list should be empty for the latest date.");

  }
  @Test void onSearchException()
  {
    assertThrows(DateTimeException.class, () -> {
      viewModel.onSearch(LocalDate.of(2020,2,0));
    }, "Invalid type of data.");
  }

  @Test void filterByTitle()
  {
  }

  @Test void filterByDate()
  {
  }

  @Test void propertyChange()
  {
  }

  @Test void addListener()
  {
  }

  @Test void removeListener()
  {
  }
}