package Controller.DialogsWindow;

import Entity.Brigade;
import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.Managers.BrigadeEntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Dialogues
{
  public static void showErrorDialogue(String header, String messageTwo)
  {
    Alert alert = new Alert(AlertType.WARNING);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("icon.png"));
    alert.setTitle("Ошибка!");
    alert.setHeaderText(header);
    alert.setContentText(messageTwo);
    alert.showAndWait();
  }

  public static int getInitialBrigade()
  {
    int choosenBrigade = -1;
    List<Brigade> brigades = new BrigadeEntityManager().getListEntities();

    List<String> choices = new ArrayList<>();
    for (Brigade brigade : brigades) {
      choices.add(String.valueOf(brigade.getNumOfBrigade()));
    }

    ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("icon.png"));
    dialog.setTitle("Выбор бригады");
    String messageOne = "Отсутствуют данные о бригаде.\nВыберите бригаду,"
        + "\nкоторая начинала прошлую неделю";
    dialog.setHeaderText(messageOne);
    dialog.setContentText("Выбор бригады: ");
    dialog.setResizable(false);


    Optional<String> result = dialog.showAndWait();
    if(result.isPresent())
    {
      choosenBrigade = Integer.parseInt(result.get());
      // -1 because brigade start it array with 0;
      DataScheduleProperty.writeProperty("firstBrigadeOfLastWeek", String.valueOf(choosenBrigade-1));
    }

    return choosenBrigade;
  }
}
