package Controller;

import Entity.Worker;
import Model.WorkerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

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
  private Button ButtonAcceptAddWorker;
  @FXML
  private TextField TextFileadName;
  @FXML
  private TextField TextFieldSecName;
  @FXML
  private TextField TextFieldPerssonelNum;
  @FXML
  private TextField TextFieldNumBrigade;
  @FXML
  private TextField isBrigadier;
  @FXML
  private CheckBox AssignBrigadier;
  @FXML
  private TableView<Worker> TableBrigade;
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


  public void IsItemSelect(MouseEvent mouseEvent)
  {
    if(TableBrigade.getSelectionModel().getSelectedItem() != null)
    {
      DispalyInformation((Worker) TableBrigade.getSelectionModel().getSelectedItem());
    }
  }

  public void removeWorker(ActionEvent event)
  {
    Worker worker = TableBrigade.getSelectionModel().getSelectedItem();
    WorkMan.remove(worker);
    clearTextFields();
    fillTable();
  }

  public void DispalyInformation(Worker worker)
  {
    String name=worker.getName();
    String secName=worker.getSecName();
    String personnelNum=worker.getPersonnelNum();
    String numOfBrigade= String.valueOf(worker.getNumOfBrigade());
    TextFileadName.setText(name);
    TextFieldSecName.setText(secName);
    TextFieldPerssonelNum.setText(personnelNum);
    TextFieldNumBrigade.setText(numOfBrigade);
    String TextIsBrigadier;
    if(worker.isBrigadier() == false)
      TextIsBrigadier ="Нет";
    else
      TextIsBrigadier ="Да";
    isBrigadier.setText(TextIsBrigadier);
  }

  private void clearTextFields()
  {
    TextFileadName.clear();
    TextFieldSecName.clear();
    TextFieldPerssonelNum.clear();
    TextFieldNumBrigade.clear();
    isBrigadier.clear();
  }

  public void addNewWorker(ActionEvent event)
  {
    clearTextFields();
    TableBrigade.setDisable(true);
    ButtonEdit.setDisable(true);
    ButtonRemove.setDisable(true);
    ButtonAcceptAddWorker.setVisible(true);
    TextFileadName.setPromptText("Введите имя");
    TextFieldSecName.setPromptText("Введите фамилию");
    TextFieldPerssonelNum.setPromptText("Введите персональный номер");
    TextFieldNumBrigade.setPromptText("Введите номер бригады");
    AssignBrigadier.setVisible(true);
    setEditableTextFields(true);
  }


  public void selectBrigadierCheckBox(ActionEvent event)
  {
    if(AssignBrigadier.isSelected())
    {
      isBrigadier.setPromptText("Бригадир: да");
    }
    else
    {
      isBrigadier.setPromptText("Бригадир: нет");
    }
  }


  public void AcceptAdd(ActionEvent event)
  {
    String name=TextFileadName.getText();
    String secName=TextFieldSecName.getText();
    String personnelNum=TextFieldPerssonelNum.getText();
    int numOfBrigade=Integer.parseInt(TextFieldNumBrigade.getText());
    boolean isBrigadier = AssignBrigadier.isSelected();
    Worker worker = new Worker(name, secName, numOfBrigade, personnelNum, isBrigadier);
    WorkMan.insert(worker);
    fillTable();
    clearTextFields();
  }

  private void setEditableTextFields(boolean inverse)
  {
    TextFileadName.setEditable(inverse);
    TextFieldSecName.setEditable(inverse);
    TextFieldPerssonelNum.setEditable(inverse);
    TextFieldNumBrigade.setEditable(inverse);
  }
}
