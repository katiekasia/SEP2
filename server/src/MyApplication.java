import javafx.application.Application;
import mediator.RemoteModel;
import mediator.RmiServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
/**
 *Main application class responsible for launching the server of the cinema bookyng system
 * Extends JavaFX's Application class to initialize and start the  interface.
 * @version 3.0   may 2024
 * @author Michal Barczuk, Kasia, Sandut, Catalina
 */
public class MyApplication extends Application
{
  private RmiServer server;
  /**
   * Entry point for launching the application.
   * Initializes the ModelManager and sets up the ,View Factory ,ViewHandler to start the application.
   * starts the server
   * @param primaryStage The primary stage for the JavaFX application.
   */
  public void start(Stage primaryStage) throws IOException, SQLException
  {
    Model model = new ModelManager();

     server = new RmiServer();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

  }

  @Override public void stop() throws Exception
  {
    server.stop();
  }
}
