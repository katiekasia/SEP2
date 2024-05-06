import client_model.Model;
import client_model.ModelManager;
import client_view.ViewHandler;
import client_viewmodel.ViewModelFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MyApplication
{public void start(Stage primaryStage) throws IOException
{
  Model model = new ModelManager();

  ViewModelFactory viewModelFactory = new ViewModelFactory(model);
  ViewHandler view = new ViewHandler(viewModelFactory);
  view.start(primaryStage);



}
}
