package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;
/**
 * Manages the ViewControllers on the client side.
 * @version 3.0   May 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class ViewHandler
{
  private ViewModelFactory modelFactory;
  private RegisterViewController registerViewController;
  private MainViewController mainViewController;
  private LoginViewController loginViewController;
  private SeatMappingViewController seatMappingViewController;
  private TransitionPageViewController transitionPageViewController;
  private SnackSelectionViewController snackSlectionViewController;
  private TicketConfirmationViewController ticketConfirmationViewController;
  private OrderConfirmationViewController orderConfirmationViewController;
  private OrderDetailsViewController orderDetailsViewController;
  private AdminPageViewController adminPageViewController;

  private AddMoviePageViewController addMoviePageViewController;
  private AddScreeningViewController addScreeningViewController;
  private EditPricesViewController editPricesViewController;

  private ManageViewController manageViewController;
  private Stage primaryStage;
  private Scene currentScene;
  /**
   * Initialises a ViewHandler object with ViewModelFactory.
   * Initializes the ViewModelFactory and creates a new Scene object with an empty Region.
   * @param modelFactory The ViewModelFactory to be used for creating view models.
   */
  public ViewHandler(ViewModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
    currentScene = new Scene(new Region());
  }
  /**
   * Sets up the primary stage for the application and opens the login view.
   * @param primaryStage The primary stage of the JavaFX application.
   */
  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
    openView("login");
  }
  /**
   * Closes the primary stage of the application.
   */
  public void close(){
    primaryStage.close();
  }
  /**
   * Opens a view based on the provided identifier.
   * Loads the corresponding FXML file and sets it as the root of the current scene.
   * @param id The identifier of the view to be opened.
   */
  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "mainPage" -> root = loadMainView("MainPage.fxml");
      case "transitionPage" -> root = loadTransitionPageView("TransitionPage.fxml");
      case "seatMapping" -> root = loadSeatMappingView("SeatMapping.fxml");
      case "ticketConfirmation" -> root = loadTicketConfirmationView("TicketConfirmation.fxml");
      case "snackSelection" -> root = loadSnackSelectionView("SnackSelection.fxml");
      case "login" -> root = loadLoginView("Login page.fxml");
      case "registerPage" -> root = loadRegisterView("Registerpage.fxml");
      case "managePage" -> root= loadManageView("ManagePage.fxml");
      case "orderConfirmation" -> root = loadOrderConfirmationView("OrderConfirmation.fxml");
      case "orderDetails" -> root = loadOrderDetailsView("OrderDetails.fxml");
      case "adminPage" -> root = loadAdminPageView("AdminPage.fxml");
      case "addMovie" -> root = loadAddMovieView("AddMovie.fxml");
      case "addScreening" -> root = loadAddScreeningView("AddScreening.fxml");
      case "editPrices" -> root = loadEditPricesView("EditPrices.fxml");

    }
    if (primaryStage.isShowing())
    {
      primaryStage.close();
    }
    currentScene.setRoot(root);
    primaryStage.setScene(currentScene);
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setResizable(false);
    primaryStage.show();
  }
  /**
   * Loads the admin page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the admin page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadAdminPageView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      adminPageViewController = loader.getController();
      adminPageViewController.init(this,root, modelFactory.getAdminPageViewModel());
    } catch (Exception e) {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }
    return root;
  }
  /**
   * Loads the login page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the login page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadLoginView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      loginViewController = loader.getController();
      loginViewController.init(this, modelFactory.getLoginViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the Main View  from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the main view page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadMainView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      mainViewController = loader.getController();
      mainViewController.init(this, root, modelFactory.getPageViewModel());
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the SeatMappingView page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the SeatMappingView page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadSeatMappingView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      seatMappingViewController = loader.getController();
      seatMappingViewController.init(this, modelFactory.getSeatMappingViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the register page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the register page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadRegisterView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      registerViewController = loader.getController();
      registerViewController.init(this, modelFactory.getRegisterViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the transition page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the transition page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadTransitionPageView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      transitionPageViewController = loader.getController();
      transitionPageViewController.init(this, modelFactory.getTransitionPageViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the snack selection page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the snack selection page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadSnackSelectionView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      snackSlectionViewController = loader.getController();
      snackSlectionViewController.init(this,modelFactory.getSnackSelectionViewModel(),root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the ticket confirmation page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file representing the ticket confirmation page view.
   * @return The root node of the loaded admin page view.
   */
  private Region loadTicketConfirmationView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      ticketConfirmationViewController = loader.getController();
      ticketConfirmationViewController.init(this, modelFactory.getTicketConfirmationViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the order confirmation page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
  private Region loadOrderConfirmationView(String fxmlFile){
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
       orderConfirmationViewController = loader.getController();
      orderConfirmationViewController.init(this, modelFactory.getOrderConfirmationViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the order details page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
  private Region loadOrderDetailsView(String fxmlFile){
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      orderDetailsViewController = loader.getController();
      orderDetailsViewController.init(this, modelFactory.getOrderDetailsViewModel(), root);
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the manage  page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
  private Region loadManageView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      manageViewController = loader.getController();
      manageViewController.init(this, root, modelFactory.getManageViewModel());
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the add movies page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
   private Region loadAddMovieView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      addMoviePageViewController = loader.getController();
      addMoviePageViewController.init(this, root, modelFactory.getAddMovieViewModel());
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the AddScreening page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
  private Region loadAddScreeningView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      addScreeningViewController = loader.getController();
      addScreeningViewController.init(this,root, modelFactory.getAddScreeningViewModel());
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }
  /**
   * Loads the edit prices page view from the provided FXML file.
   *
   * @param fxmlFile The path to the FXML file
   * @return The root node of the loaded admin page view.
   */
  private Region loadEditPricesView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      editPricesViewController = loader.getController();
      editPricesViewController.init(this,root, modelFactory.getEditPricesViewModel());
    }
    catch (Exception e)
    {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }

    return root;
  }


}
