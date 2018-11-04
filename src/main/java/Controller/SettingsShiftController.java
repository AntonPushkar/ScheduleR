package Controller;

import Entity.Day;
import Entity.DayOff;
import Model.Managers.DayManager;
import Model.Managers.DayOffManager;
import Model.Managers.Manager;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class SettingsShiftController
{
  private DayOffManager dayOffManager = new DayOffManager();
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
  @FXML
  private TableView TableDates;
  @FXML
  private TableColumn ColumnDates;

  private boolean selectShifts=true;

  @FXML
  public void initialize()
  {
    fillTableShifts();
  }


  public void IsItemSelect(MouseEvent mouseEvent)
  {

  }

  public void SelectDaysOff(Event event)
  {
    if(selectShifts) {
      invertElements();
      selectShifts = false;
      TableDates.getItems().clear();
      fillTableDaysOff();
    }
  }

  public void SelectShifts(Event event)
  {
     if(!selectShifts) {
        invertElements();
        selectShifts = true;
        TableDates.getItems().clear();
        fillTableShifts();
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

  public void fillTableShifts()
  {
    fillTable(new DayManager());
  }

  public void fillTableDaysOff()
  {
   fillTable(new DayOffManager());
  }

  public void fillTable(Manager<?> manager) {
    TableDates.getItems().clear();
    DayManager dayOffManager = new DayManager();
    ObservableList listDates =
        FXCollections.observableArrayList(manager.getListOfEntities());
    ColumnDates.setCellValueFactory(new PropertyValueFactory("formatterDate"));
    TableDates.setItems(listDates);
  }
  public void BtnAcceptDayOff(ActionEvent event)
  {
    LocalDate date = DaysOffDatePicker.getValue();
    if(date != null)
    {
      System.out.println("OK");
      DayOff dayOff = new DayOff(date);
      dayOffManager.insert(dayOff);
      fillTableDaysOff();
    }
  }

  public void IsItemSelectShifts(MouseEvent mouseEvent)
  {

    if(TableDates.getSelectionModel().getSelectedItem() instanceof Day)
    {
      Day day = (Day) TableDates.getSelectionModel().getSelectedItem();
      displayDay(day);
    }
    else if (TableDates.getSelectionModel().getSelectedItem() instanceof DayOff)
    {
    }
  }

  public void displayDay(Day day)
  {

    TextFieldFirstShift.setText(String.valueOf(day.getBrigadeDay()));
    TextFieldSecondShift.setText(String.valueOf(day.getBrigadeNight()));
    String isDayOff = "";
    if(day.isDayOff())
      isDayOff = "Да";
    else
      isDayOff="Нет";
    TextFieldIsDayOff.setText(isDayOff);
  }

}
