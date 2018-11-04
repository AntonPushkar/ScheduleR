package Controller;

import Controller.DialogsWindow.DialogueMainWindow;
import Entity.Brigade;
import Entity.Day;
import Entity.Schedule;
import Main.BrigadeWindow;
import Main.SetupShiftsWindow;
import Model.CreaterSchedule.ScheduleCreater;
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

  @FXML
  private Button BrigadeButton;
  @FXML
  private Button SettingsShift;
  @FXML
  private Button PrintButton;
  @FXML
  private TableView<Day> scheduleTable;
  @FXML
  private TableColumn<Day, LocalDate> dateCollumn;
  @FXML
  private TableColumn<Day, Brigade> firstShiftColumn;
  @FXML
  private TableColumn<Day, Brigade> secondShiftColumn;
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
    if(scheduleCreater==null) {
      DialogueMainWindow.dontChooseDate();
      return;
    }
    fillTable();
  }


  public void fillTable()
  {
    ObservableList<Entity.Day> listOfSchedule =
        FXCollections.observableArrayList(scheduleCreater.createSchedule());
    dateCollumn.setCellValueFactory(new PropertyValueFactory<Day, LocalDate >("formatterDate"));
    firstShiftColumn.setCellValueFactory(new PropertyValueFactory<Day, Brigade >("brigadeDay"));
    secondShiftColumn.setCellValueFactory(new PropertyValueFactory<Day, Brigade >("brigadeNight"));
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
    scheduleCreater = new ScheduleCreater(date);
  }
}
