import mediator.RmiClient;
import model.Model;
import model.ModelManager;
import view.ViewHandler;
import viewmodel.ViewModelFactory;
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
  @Override public void stop(){

  }
}
