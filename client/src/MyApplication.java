import client_mediator.RmiClient;
import client_model.Model;
import client_model.ModelManager;
import client_view.ViewHandler;
import client_viewmodel.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends Application
{public void start(Stage primaryStage) throws IOException
{
  RmiClient client = new RmiClient("localhost");
  Model model = new ModelManager(client);

  ViewModelFactory viewModelFactory = new ViewModelFactory(model);
  ViewHandler view = new ViewHandler(viewModelFactory);
  view.start(primaryStage);



}
}
