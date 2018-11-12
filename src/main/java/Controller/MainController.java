package Controller;

import Controller.DialogsWindow.DialogueMainWindow;
import Entity.Brigade;
import Entity.Day;
import Entity.ScheduleWrapperForTable;
import Main.BrigadeWindow;
import Main.SetupShiftsWindow;
import Model.CreaterSchedule.ScheduleCreater;
import Model.CreaterSchedule.ScheduleManager;
import Model.Managers.BrigadeManager;
import Model.Managers.WorkerManager;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class MainController
{

  private ScheduleCreater scheduleCreater;
  private ScheduleManager manager;

  @FXML
  private Button BrigadeButton;
  @FXML
  private Button SettingsShift;
  @FXML
  private Button PrintButton;
  @FXML
  private TableView<ScheduleWrapperForTable> scheduleTable;
  @FXML
  private TableColumn<ScheduleWrapperForTable, String> dateCollumn;
  @FXML
  private TableColumn<ScheduleWrapperForTable, String> firstShiftColumn;
  @FXML
  private TableColumn<ScheduleWrapperForTable, String> secondShiftColumn;
  @FXML
  private DatePicker datePickerSchedule;
  @FXML
  public void initialize()
  {
    new WorkerManager().getListOfEntities();
    new BrigadeManager().fillBrigade();
  }

  public void createToSchedule(ActionEvent event)
  {
    if(manager==null) {
      DialogueMainWindow.dontChooseDate();
      return;
    }
    else {
      manager.createSchedule();
      fillTable();
    }
  }


  public void fillTable()
  {

    ObservableList<Entity.ScheduleWrapperForTable> listOfSchedule =
        FXCollections.observableArrayList(manager.getSchedule());
    dateCollumn.setCellValueFactory(new PropertyValueFactory<>("date"));
    firstShiftColumn.setCellValueFactory(new PropertyValueFactory<>("brigadeDay"));
    secondShiftColumn.setCellValueFactory(new PropertyValueFactory<>("brigadeNight"));
    scheduleTable.setItems(listOfSchedule);
  }


  public void ClickBrigade(ActionEvent event)
  {
    BrigadeWindow brigadeWindow = new BrigadeWindow();
    brigadeWindow.DisplayBrigadeWindow();
  }


  public void ClickSettingsShift(ActionEvent event)
  {
    SetupShiftsWindow shifts = new SetupShiftsWindow();
    shifts.DisplaySetupShiftWindow();
  }

  public void PrintBtn(ActionEvent event)
  {

  }

  public void PickDateShedule(ActionEvent event)
  {
    LocalDate date = datePickerSchedule.getValue();
    //scheduleCreater = new ScheduleCreater(date);
    date = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
    if(date!=null)
    {
      manager = new ScheduleManager(date);
    }
  }
}
