package Controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SettingsShiftController
{
  @FXML
  private TextField TextFieldFirstShift;
  @FXML
  private TextField TextFieldSecondShift;
  @FXML
  private TextField TextFieldIsDayOff;
  @FXML
  private DatePicker DaysOffDatePicker;
  @FXML
  private TabPane TabPaneShifts;
  @FXML
  private Tab TabShifts;
  @FXML
  private Tab TabDaysOff;
  @FXML
  private Button ButtonAcceptDayOff;
  @FXML
  private Label LableFirstShift;
  @FXML
  private Label LableSecondShift;
  @FXML
  private Label LableIsDayOff;

  private boolean selectShifts=true;

  public void IsItemSelect(MouseEvent mouseEvent) {
  }

  public void SelectDaysOff(Event event)
  {
    if(selectShifts==true) {
      invertElements();
      selectShifts=false;
    }
  }

  public void SelectShifts(Event event)
  {
    if(selectShifts==false) {
      invertElements();
      selectShifts=true;
    }
  }

  private void invertElements()
  {
    TextFieldFirstShift.setVisible(!TextFieldFirstShift.isVisible());
    TextFieldSecondShift.setVisible(!TextFieldSecondShift.isVisible());
    TextFieldIsDayOff.setVisible(!TextFieldIsDayOff.isVisible());
    DaysOffDatePicker.setVisible(!DaysOffDatePicker.isVisible());
    ButtonAcceptDayOff.setVisible(!ButtonAcceptDayOff.isVisible());
    LableFirstShift.setVisible(!LableFirstShift.isVisible());
    LableSecondShift.setVisible(!LableSecondShift.isVisible());
    LableIsDayOff.setVisible(!LableIsDayOff.isVisible());
  }

  public void BtnAcceptDayOff(ActionEvent event)
  {
  }
}
