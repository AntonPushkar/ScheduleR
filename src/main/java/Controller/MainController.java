package Controller;

import Controller.DialogsWindow.DialogueMainWindow;
import Entity.ScheduleWrapperForTable;
import Main.BrigadeWindow;
import Main.SetupShiftsWindow;
import Model.CreaterSchedule.ScheduleManager;
import Model.Managers.BrigadeEntityManager;
import Model.Managers.WorkerEntityManager;
import Model.ReportOfSchedule;
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
import net.sf.jasperreports.engine.JRException;


public class MainController
{

  private final ScheduleManager scheduleManager = ScheduleManager.getScheduleManager();
  private LocalDate date;

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
    new WorkerEntityManager().getListEntities();
    new BrigadeEntityManager().fillBrigade();
  }

  public void createToSchedule(ActionEvent event)
  {
    if(date ==null) {
      DialogueMainWindow.notChooseDate();
    }
    else {
      generateSchedule();
      fillTable();
    }
  }


  private void fillTable()
  {

    ObservableList<Entity.ScheduleWrapperForTable> listOfSchedule =
        FXCollections.observableArrayList(scheduleManager.getListEntities());
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
    try {
      ReportOfSchedule.print();
    } catch (JRException e) {
      e.printStackTrace();
    }
  }

  public void getDate(ActionEvent event)
  {
    date = datePickerSchedule.getValue();
  }

  private void generateSchedule()
  {
    this.date = LocalDate.of(date.getYear(), date.getMonthValue(), 1);
    if(this.date!=null)
    {
      scheduleManager.createSchedule(this.date);
    }
  }

}
