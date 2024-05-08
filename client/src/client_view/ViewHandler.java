package client_view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import client_viewmodel.ViewModelFactory;

public class ViewHandler
{
  private ViewModelFactory modelFactory;
  private RegisterViewController registerViewController;
  private MainViewController mainViewController;
  private LoginViewController loginViewController;
  private SeatMappingViewController seatMappingViewController;
  private TicketBookingViewController ticketBookingViewController;
  private BookingConfirmedViewController bookingConfirmedViewController;

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
    openView("mainPage");
  }
  public void openView(String id)
  {
    Region root = null;
    switch (id)
    {
      case "mainPage" -> root = loadMainView("Main Page.fxml");
      case "ticketBooking" -> root = loadTicketBookingView("TicketBooking.fxml");
      case "bookingConfirmed"->root=loadBookingConfirmedView("Booking Confirmed.fxml");
      //ALL THE OTHERS PAGES
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
  private Region loadBookingConfirmedView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      bookingConfirmedViewController = loader.getController();
      bookingConfirmedViewController.init(this, root, modelFactory.getBookingConfirmedViewModel());
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

  private Region loadTicketBookingView(String fxmlFile)
  {
    Region root = null;
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      ticketBookingViewController = loader.getController();
      ticketBookingViewController.init(this, modelFactory.getTicketBookingViewModel(), root);
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
