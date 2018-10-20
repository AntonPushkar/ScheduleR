package Main;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SetupShiftsWindow extends MainWindow
{

  public void DisplaySetupShiftWindow()
  {
    try {
      Stage stage = new Stage();
      Parent root = FXMLLoader.load(getClass().getResource("/SettingsOfWorkShift.fxml"));
      stage.setScene(new Scene(root));
      stage.setResizable(false);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.initOwner(MainWindow.getPrimaryStage());
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
