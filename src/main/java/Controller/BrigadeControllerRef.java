package Controller;

import Model.WorkerManager;
import java.awt.Button;
import java.awt.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class BrigadeControllerRef
{
  private WorkerManager WorkMan = new WorkerManager();
  @FXML
  private Button ButtonAdd;
  @FXML
  private Button ButtonEdit;
  @FXML
  private Button ButtonRemove;
  @FXML
  private Button ButtonOk;
  @FXML
  private TextField TextFileadName;
  @FXML
  private TextField TextFieldSecName;
  @FXML
  private TextField TextFieldPerssonelName;
  @FXML
  private TextField TextFieldNumBrigade;
  @FXML
  private TextField isBrigadier;
  @FXML
  private CheckBox AssignBrigadier;
  @FXML
  private TableView TableBrigade;
  @FXML
  private TableColumn<Entity.Worker, String> ColumnFIO;
  @FXML
  private TableColumn<Entity.Worker, Integer> ColumnBrigade;

  public void initialize()
  {
    TableBrigade.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    fillTable();
  }

  public void fillTable()
  {
    ObservableList<Entity.Worker> listOfWorkers = FXCollections.observableArrayList(WorkMan.getListOfEntities());
    ColumnFIO.setCellValueFactory(new PropertyValueFactory<Entity.Worker, String>("fullName"));
    ColumnBrigade.setCellValueFactory(new PropertyValueFactory<Entity.Worker, Integer>("numOfBrigade"));
    TableBrigade.setItems(listOfWorkers);
  }
  
}
