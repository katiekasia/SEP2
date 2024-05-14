import javafx.application.Application;
import mediator.RemoteModel;
import mediator.RmiServer;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
    Model model = new ModelManager();
   /*
    if (System.getSecurityManager() == null)
    {
      System.setSecurityManager(new SecurityManager());
    }

*/
    RemoteModel server = new RmiServer();
    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

  }

}
