import javafx.application.Application;
import server_model.Model;
import server_model.ModelManager;
import server_view.ViewHandler;
import server_viewmodel.ViewModelFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication extends Application
{
  public void start(Stage primaryStage) throws IOException
  {
    Model model = new ModelManager();

    ViewModelFactory viewModelFactory = new ViewModelFactory(model);
    ViewHandler view = new ViewHandler(viewModelFactory);
    view.start(primaryStage);

  }
}
