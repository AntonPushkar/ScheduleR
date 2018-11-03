package Controller.DialogsWindow;

import Entity.Brigade;
import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.Managers.BrigadeManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DialogueWindowInitialData
{
  private static String messageOne = "Отсутствуют данные о бригаде.\nВыберите бригаду,"
      + "\nкоторая начинала прошлую неделю";

  public static int getInitialBrigade()
  {
    int choosenBrigade = -1;
    List<Brigade> brigades = new BrigadeManager().getListOfEntities();

    List<String> choices = new ArrayList<>();
    for(int i=0; i<brigades.size(); i++)
    {
      choices.add(String.valueOf(brigades.get(i).getNumOfBrigade()));
    }

    ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
    Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("icon.png"));
    dialog.setTitle("Выбор бригады");
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