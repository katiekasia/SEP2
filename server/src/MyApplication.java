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

public class MyApplication extends Application
{
  private RmiServer server;
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
