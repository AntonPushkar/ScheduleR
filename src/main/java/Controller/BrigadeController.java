package Controller;

import Controller.DialogsWindow.DialogueWindowIErrorBrigade;
import Controller.Validators.ValidateWorkerFields;
import Entity.Worker;
import Model.Managers.WorkerEntityManager;
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

public class BrigadeController
{
  private final WorkerEntityManager WorkMan = new WorkerEntityManager();
  @FXML
  private Button ButtonAdd;
  @FXML
  private Button ButtonEdit;
  @FXML
  private Button ButtonRemove;
  @FXML
  private Button ButtonAcceptAddWorker;
  @FXML
  private Button CancelButton;
  @FXML
  private Button AcceptEditWorker;
  @FXML
  private TextField TextFieldName;
  @FXML
  private TextField TextFieldSecName;
  @FXML
  private TextField TextFieldPersonnelNum;
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


  private void fillTable()
  {
    ObservableList<Entity.Worker> listOfWorkers = FXCollections.observableArrayList(WorkMan.getListEntities());
    ColumnFIO.setCellValueFactory(new PropertyValueFactory<>("fullName"));
    ColumnBrigade.setCellValueFactory(new PropertyValueFactory<>("numOfBrigade"));
    TableBrigade.setItems(listOfWorkers);
  }




  public void IsItemSelect(MouseEvent mouseEvent)
  {
    if(TableBrigade.getSelectionModel().getSelectedItem() != null)
    {
      DisplayInformation(TableBrigade.getSelectionModel().getSelectedItem());
      ButtonEdit.setDisable(false);
    }
  }

  public void removeWorker(ActionEvent event)
  {
    Worker worker = TableBrigade.getSelectionModel().getSelectedItem();
    if(worker != null) {
      WorkMan.remove(worker);
      clearTextFields();
      fillTable();
    }
    else
      DialogueWindowIErrorBrigade.AlertNotSelected();
  }

  private void DisplayInformation(Worker worker)
  {
    String name=worker.getName();
    String secName=worker.getSecName();
    String personnelNum=worker.getPersonnelNum();
    String numOfBrigade= String.valueOf(worker.getNumOfBrigade());
    TextFieldName.setText(name);
    TextFieldSecName.setText(secName);
    TextFieldPersonnelNum.setText(personnelNum);
    TextFieldNumBrigade.setText(numOfBrigade);
    String TextIsBrigadier;
    if(!worker.isBrigadier())
      TextIsBrigadier ="Нет";
    else
      TextIsBrigadier ="Да";
    isBrigadier.setText(TextIsBrigadier);
  }

  private void clearTextFields()
  {
    TextFieldName.clear();
    TextFieldSecName.clear();
    TextFieldPersonnelNum.clear();
    TextFieldNumBrigade.clear();
    isBrigadier.clear();
    TextFieldName.setPromptText("");
    TextFieldSecName.setPromptText("");
    TextFieldPersonnelNum.setPromptText("");
    TextFieldNumBrigade.setPromptText("");
    isBrigadier.setPromptText("");
  }

  public void addNewWorker(ActionEvent event)
  {
    clearTextFields();
    ButtonAdd.setVisible(false);
    CancelButton.setVisible(true);
    TableBrigade.setDisable(true);
    ButtonEdit.setDisable(true);
    ButtonRemove.setDisable(true);
    ButtonAcceptAddWorker.setVisible(true);
    TextFieldName.setPromptText("Введите имя");
    TextFieldSecName.setPromptText("Введите фамилию");
    TextFieldPersonnelNum.setPromptText("Введите персональный номер");
    TextFieldNumBrigade.setPromptText("Введите номер бригады");
    AssignBrigadier.setVisible(true);
    setEditableTextFields(true);
  }


  public void selectBrigadierCheckBox(ActionEvent event)
  {
    if(AssignBrigadier.isSelected())
    {
      isBrigadier.setText("Бригадир: да");
    }
    else
    {
      isBrigadier.setText("Бригадир: нет");
    }
  }


  public void AcceptAdd(ActionEvent event)
  {
    if(validate()) {
      boolean isBrigadier = AssignBrigadier.isSelected();
      int numOfBrigade=Integer.parseInt(TextFieldNumBrigade.getText());
      Worker worker = new Worker(TextFieldName.getText(), TextFieldSecName.getText(),
          numOfBrigade, TextFieldPersonnelNum.getText(), isBrigadier);
      WorkMan.insert(worker);
      fillTable();
      cancel();
    }
    else
    {
      DialogueWindowIErrorBrigade.AlertInTextField();
    }
  }

  private void setEditableTextFields(boolean inverse)
  {
    TextFieldName.setEditable(inverse);
    TextFieldSecName.setEditable(inverse);
    TextFieldPersonnelNum.setEditable(inverse);
    TextFieldNumBrigade.setEditable(inverse);
  }

  public void cancel(ActionEvent event)
  {
    cancel();
  }

  private void cancel()
  {
    clearTextFields();
    setEditableTextFields(false);
    CancelButton.setVisible(false);
    TableBrigade.setDisable(false);
    ButtonEdit.setDisable(false);
    ButtonRemove.setDisable(false);
    ButtonAcceptAddWorker.setVisible(false);
    ButtonAdd.setVisible(true);
    AssignBrigadier.setVisible(false);
    AcceptEditWorker.setVisible(false);
    TableBrigade.getSelectionModel().clearSelection();
  }

  public void editWorker(ActionEvent event)
  {
    if(TableBrigade.getSelectionModel().getSelectedItem()==null)
    {
      DialogueWindowIErrorBrigade.AlertNotSelected();
      return;
    }
    ButtonAdd.setVisible(false);
    ButtonEdit.setDisable(true);
    ButtonRemove.setDisable(true);
    CancelButton.setVisible(true);
    AcceptEditWorker.setVisible(true);
    TableBrigade.setDisable(true);
    AssignBrigadier.setVisible(true);
    setEditableTextFields(true);
    TextFieldPersonnelNum.setEditable(false);

  }

  private boolean validate()
  {
    String name= TextFieldName.getText();
    String secName=TextFieldSecName.getText();
    String personnelNum= TextFieldPersonnelNum.getText();
    String numOfBrigadeStr = TextFieldNumBrigade.getText();
    return ValidateWorkerFields.validateWorker(name, secName, numOfBrigadeStr, personnelNum);
  }

  public void AcceptEdit(ActionEvent event)
  {

    if(validate()) {
      boolean isBrigadier = AssignBrigadier.isSelected();
      int numOfBrigade=Integer.parseInt(TextFieldNumBrigade.getText());
      Worker worker = new Worker(TextFieldName.getText(), TextFieldSecName.getText(),
          numOfBrigade, TextFieldPersonnelNum.getText(), isBrigadier);
      WorkMan.update(worker);
      fillTable();
      cancel();
    }
    else
    {
      DialogueWindowIErrorBrigade.AlertInTextField();
    }
  }
}
