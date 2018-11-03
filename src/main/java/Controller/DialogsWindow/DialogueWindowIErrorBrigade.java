package Controller.DialogsWindow;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DialogueWindowIErrorBrigade
{

  public static void AlertInTextField()
  {
    Alert alert = new Alert(AlertType.WARNING);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("icon.png"));
    alert.setTitle("Ошибка!");
    alert.setHeaderText("Проверьте правильность ввода данных!");
    String textContent = "Поля не могут быть пустыми, или с неверными значениями. Имя, Фамилия, номер,"
        + " - не пусты. Номер бригады только цифры!";
    alert.setContentText(textContent);
    alert.showAndWait();
  }

  public static void AlertNotSelected()
  {
    Alert alert = new Alert(AlertType.WARNING);
    Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
    stage.getIcons().add(new Image("icon.png"));
    alert.setTitle("Ошибка!");
    alert.setHeaderText("Не выбран ни один человек");
    String textContent = "Выберите человека из списка";
    alert.setContentText(textContent);
    alert.showAndWait();
  }
}
