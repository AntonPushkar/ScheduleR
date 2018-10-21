package Controller;

import Entity.Worker;
import Model.WorkerManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class BrigadeController {

  @FXML
  private TableView<Worker> TableBrigade;
  @FXML
  private TableColumn<Worker, String> fioColumn;
  @FXML
  private TableColumn<Worker, Integer> numOfBrigadeColumn;
  @FXML
  private TextField inputNewWorker;
  @FXML
  private ChoiceBox<Integer> choiceBoxOfNumBrigade;
  @FXML
  private Button removeWorker;
  @FXML
  public void initialize()
  {
    choiceBoxOfNumBrigade.setValue(1);
    choiceBoxOfNumBrigade.setItems(fillChoiceBoxNumOfBrigade(3));
    TableBrigade.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    fillTable();
  }

  public ObservableList<Integer> fillChoiceBoxNumOfBrigade(int numOfBrigades)
  {
    ObservableList<Integer> brigadesList = FXCollections.observableArrayList();
    for(int i=1; i<=numOfBrigades; i++)
      brigadesList.add(i);
    return brigadesList;
  }

  public void BtnAcceptNewWorker(ActionEvent event)
  {
    String fullName = inputNewWorker.getText();
    int numBrigade = choiceBoxOfNumBrigade.getValue();
    WorkerManager.GenerateWorker(fullName, numBrigade);
    fillTable();
  }

  public void fillTable()
  {
    ObservableList<Worker> listOfWorkers = FXCollections.observableArrayList(WorkerManager.getWorker());
    fioColumn.setCellValueFactory(new PropertyValueFactory<Worker, String>("fullName"));
    numOfBrigadeColumn.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("numOfBrigade"));
    TableBrigade.setItems(listOfWorkers);
  }


  public void removeWorker(ActionEvent event)
  {
    Worker worker = TableBrigade.getSelectionModel().getSelectedItem();
    WorkerManager.remove(worker);
    fillTable();
    TableBrigade.getSelectionModel().selectFirst();
  }

  public void selectItem(MouseEvent mouseEvent)
  {
    if(TableBrigade.getSelectionModel().getSelectedItem() != null)
    {
      removeWorker.setDisable(false);
    }
  }
}
