package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import viewmodel.ViewModelFactory;

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

  private ManageViewController manageViewController;
  private Stage primaryStage;
  private Scene currentScene;

  public ViewHandler(ViewModelFactory modelFactory)
  {
    this.modelFactory = modelFactory;
    currentScene = new Scene(new Region());
  }
  public void start(Stage primaryStage)
  {
    this.primaryStage = primaryStage;
//!!!!!!
    /*
    later change to Login page after the correct SPRINT
     */
    openView("login");
  }
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
  private Region loadAdminPageView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      adminPageViewController = loader.getController();
      adminPageViewController.init(this, root, modelFactory.getAdminPageViewModel());
    } catch (Exception e) {
      System.out.println(
          "\n\n--------- Cannot load the correct page! :(( ---------\n\n");
      e.printStackTrace();
    }
    return root;
  }
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

}
