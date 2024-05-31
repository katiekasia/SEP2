import mediator.RmiClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
/**
 *Main application class responsible for starting the client side of the cinema bookyng system
 * Extends JavaFX's Application class to initialize and start the  interface.
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class MyApplication extends Application
{
  private RmiClient client;
  /**
   * Entry point for launching the application.
   * Initializes the ModelManager and sets up the View Factory ,ViewHandler to start the application.
   * starts the client
   * @param primaryStage The primary stage for the JavaFX application.
   */
  public void start(Stage primaryStage) throws IOException
{
  client = new RmiClient("localhost");
  Model model = new ModelManager(client);

  ViewModelFactory viewModelFactory = new ViewModelFactory(model);
  ViewHandler view = new ViewHandler(viewModelFactory);
  view.start(primaryStage);
}
  @Override public void stop()
  {
    client.stop();
  }
}
