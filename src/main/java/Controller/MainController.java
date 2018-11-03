package Controller;

import Controller.DialogsWindow.DialogueMainWindow;
import Model.CreaterSchedule.Validators.ValidateInitialData;
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
  private TableView<Schedule> scheduleTable;
  @FXML
  private TableColumn<Schedule, String> dateCollumn;
  @FXML
  private TableColumn<Schedule, String> firstShiftColumn;
  @FXML
  private TableColumn<Schedule, String> secondShiftColumn;
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
    removeItem();
    ObservableList<Entity.Schedule> listOfWorkers =
        FXCollections.observableArrayList(scheduleCreater.createSchedule());
    dateCollumn.setCellValueFactory(new PropertyValueFactory<Schedule, String >("date"));
    firstShiftColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String >("bridageDay"));
    secondShiftColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String >("bridageNigth"));
    scheduleTable.setItems(listOfWorkers);
  }
  public void removeItem()
  {
    for(int i=0; i<scheduleTable.getItems().size(); i++)
    {
      scheduleTable.getItems().clear();
    }
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
