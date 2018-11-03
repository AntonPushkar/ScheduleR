package Controller.DialogsWindow;

import Entity.Brigade;
import Model.Managers.BrigadeManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ChoiceDialog;

public class InitialData
{
  public int getInitialBrigade()
  {
    int choosenBrigade = -1;
    List<Brigade> brigades = new BrigadeManager().getListOfEntities();

    List<String> choices = new ArrayList<>();
    for(int i=0; i<brigades.size(); i++)
    {
      choices.add(String.valueOf(brigades.get(i).getNumOfBrigade()));
    }

    ChoiceDialog<String> dialog = new ChoiceDialog<>(choices.get(0),choices);
    dialog.setTitle("Выбор бригады");
    dialog.setHeaderText("Отсутствуют данные о бригаде. Выберите бригаду, которая начинала прошлую неделю");
    dialog.setContentText("Выбор бригады: ");

    Optional<String> result = dialog.showAndWait();
    if(result.isPresent())
    {
      choosenBrigade = Integer.parseInt(result.get());
    }
    return choosenBrigade;
  }

}
