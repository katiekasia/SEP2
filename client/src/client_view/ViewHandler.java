package client_view;

import client_viewmodel.ViewModelFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class ViewHandler {
  private ViewModelFactory factory;
  private Stage primaryStage;
  private Scene currentScene;

  private MainPageViewController mainPageViewController;
  private LoginViewController loginViewController;
  private RegisterPageViewController registerPageViewController;
  private SeatMappingViewController seatMappingViewController;

  public ViewHandler(ViewModelFactory factory) {
    this.factory = factory;
    currentScene = new Scene(new Region());
  }

  public void start(Stage primaryStage) {
    this.primaryStage = primaryStage;
    openView("login");
  }

  public void openView(String id) {
    Region root = null;
    switch (id) {
      case "MainPage":
        root = loadMainPageView("Main Page.fxml");
        break;
      case "login":
        root = loadLoginView("Login Page.fxml");
        break;
      case "register":
        root = loadRegisterView("Register Page.fxml");
        break;
      case "seatMapping":
        root = loadSeatMappingView("Seat Mapping.fxml");
        break;
    }
    if (primaryStage.isShowing()) {
      primaryStage.close();
    }
    currentScene.setRoot(root);
    primaryStage.setScene(currentScene);
    primaryStage.setHeight(root.getPrefHeight());
    primaryStage.setWidth(root.getPrefWidth());
    primaryStage.setResizable(false);
    primaryStage.show();
  }

  public Region loadLoginView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      loginViewController = loader.getController();
      loginViewController.init(this, factory.getLoginViewModel());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return root;
  }

  public Region loadMainPageView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      mainPageViewController = loader.getController();
      mainPageViewController.init(this, factory.getMainPageViewModel());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return root;
  }

  public Region loadRegisterView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      registerPageViewController = loader.getController();
      registerPageViewController.init(this, factory.getRegisterPageViewModel());
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Exception occurred while loading RegisterPageView: " + e.getMessage());
    }
    return root;
  }

  public Region loadSeatMappingView(String fxmlFile) {
    Region root = null;
    try {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource(fxmlFile));
      root = loader.load();
      seatMappingViewController = loader.getController();
      seatMappingViewController.init(this, factory.getSeatMappingViewModel());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return root;
  }

  public void closeView() {
    primaryStage.close();
  }
}

