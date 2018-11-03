package Controller.DialogsWindow;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DialogsErrorBrigade
{
  private static Alert alert;
  public static void AlertInTextField()
  {
    Alert alert = new Alert(AlertType.WARNING);
    alert.setTitle("Ошибка!");
    alert.setHeaderText("Проверьте правильность ввода данных!");
    String textContent = "Поля не могут быть пустыми, или с неверными значениями. Имя, Фамилия, номер,"
        + " - не пусты. Номер бригады только цифры!";
    alert.setContentText(textContent);
    alert.showAndWait();
  }

  public static void AlertNotSelected()
  {
    alert = new Alert(AlertType.WARNING);
    alert.setTitle("Ошибка!");
    alert.setHeaderText("Не выбран ни один человек");
    String textContent = "Выберите человека из списка";
    alert.setContentText(textContent);
    alert.showAndWait();
  }
}
