package Main;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainWindow extends Application
{

  private static Stage primaryStage;

  public static void main(String[] args)
  {
    launch();
  }


  public void start(Stage stage) throws Exception {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
      primaryStage = stage;
      stage.setScene(new Scene(root));
      stage.getIcons().add(new Image("icon.png"));
      stage.setMinHeight(520);
      stage.setMinWidth(520);
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static Stage getPrimaryStage()
  {
    return primaryStage;
  }
}
