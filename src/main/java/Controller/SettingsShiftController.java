package Controller;

import Entity.Day;
import Entity.DayOff;
import Entity.ScheduleWrapperForTable;
import Model.CreaterSchedule.ScheduleManager;
import Model.EditCreateDaysOff;
import Model.Managers.DayOffEntityManager;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
  private DayOffEntityManager dayOffManager = new DayOffEntityManager();
  private ScheduleManager scheduleManager = ScheduleManager.getScheduleManager();
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
  private TableView<ScheduleWrapperForTable> TableDatesShifts;
  @FXML
  private TableColumn<ScheduleWrapperForTable, String> ColumnDatesShifts;
  @FXML
  private TableView<DayOff> TableDatesDaysOff;
  @FXML
  private TableColumn<DayOff, String> ColumnDatesDaysOff;
  @FXML
  private CheckBox cancelFirstShift;
  @FXML
  private CheckBox cancelSecondShift;
  @FXML
  private CheckBox setDayOff;
  @FXML
  private Button ButtonAcceptChangeInShifts;

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
      cancelFirstShift.setSelected(false);
      cancelSecondShift.setSelected(false);
      selectShifts = false;
      if(TableDatesDaysOff.getItems().isEmpty())
        fillTableDaysOff();
    }
  }

  public void SelectShifts(Event event)
  {
     if(!selectShifts) {
        invertElements();
       cancelFirstShift.setSelected(false);
       cancelSecondShift.setSelected(false);
        selectShifts = true;
        if(TableDatesShifts.getItems().isEmpty())
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
    TableDatesShifts.setVisible(!TableDatesShifts.isVisible());
    TableDatesDaysOff.setVisible(!TableDatesShifts.isVisible());
    ButtonAcceptChangeInShifts.setVisible(!ButtonAcceptChangeInShifts.isVisible());
    setDayOff.setVisible(!setDayOff.isVisible());
  }

  public void fillTableShifts()
  {
    if(!TableDatesShifts.getItems().isEmpty()) TableDatesShifts.getItems().clear();
    ObservableList<ScheduleWrapperForTable> list =
        FXCollections.observableList(scheduleManager.getListEntities());
    String field = "date";
    fillTable(TableDatesShifts, ColumnDatesShifts, list, field);
  }

  public void fillTableDaysOff()
  {
    if(!TableDatesDaysOff.getItems().isEmpty()) TableDatesShifts.getItems().clear();
    ObservableList<DayOff> list =  FXCollections.observableList(dayOffManager.getListEntities());
    String field = "date";
    fillTable(TableDatesDaysOff, ColumnDatesDaysOff, list, field);
  }

  public void fillTable(TableView table, TableColumn tableColumn, ObservableList list, String field) {
    if(!table.getItems().isEmpty())
      table.getItems().clear();
    tableColumn.setCellValueFactory(new PropertyValueFactory(field));
    table.getItems().addAll(list);
  }
  public void BtnAcceptDayOff(ActionEvent event)
  {
    LocalDate date = DaysOffDatePicker.getValue();
    if(date != null)
    {
      System.out.println("OK");
      DayOff dayOff = new DayOff(date, setDayOff.isSelected(),  cancelFirstShift.isSelected(), cancelSecondShift.isSelected());
      dayOffManager.insert(dayOff);
      fillTableDaysOff();
    }
  }

  public void IsItemSelectShifts(MouseEvent mouseEvent)
  {
    ScheduleWrapperForTable day = TableDatesShifts.getSelectionModel().getSelectedItem();
    displayDay(day);
  }

  public void displayDay(ScheduleWrapperForTable day)
  {
    TextFieldFirstShift.setText(String.valueOf(day.getBrigadeDay()));
    TextFieldSecondShift.setText(String.valueOf(day.getBrigadeNight()));
    String isDayOff = "";
    if(day.getBrigadeDay().equals("Выходной") && day.getBrigadeNight().equals("Выходной"))
      isDayOff = "Да";
    else
      isDayOff="Нет";
    TextFieldIsDayOff.setText(isDayOff);
  }

  public void IsItemSelectDaysOff(MouseEvent mouseEvent)
  {
    DayOff day = TableDatesDaysOff.getSelectionModel().getSelectedItem();
  }

  public void BtnAcceptChangeInShifts(ActionEvent actionEvent)
  {
    ScheduleWrapperForTable wrapperDay = TableDatesShifts.getSelectionModel().getSelectedItem();
    if(wrapperDay!=null) {
      Day day = wrapperDay.getDayOfSchedule();
      if (cancelFirstShift.isSelected())
        day.setCancelFirstShift(true);
      if (cancelSecondShift.isSelected())
        day.setCancelSecondShift(true);
      EditCreateDaysOff.analizeDayOff(day);
      changeWrapperDay(day, wrapperDay);
      displayDay(wrapperDay);
    }
  }

  private void changeWrapperDay(Day day, ScheduleWrapperForTable wrapper)
  {
    if(day.isCancelFirstShift())
      wrapper.setBrigadeDay("Выходной");
    if(day.isCancelSecondShift())
      wrapper.setBrigadeNight("Выходной");
  }
}
