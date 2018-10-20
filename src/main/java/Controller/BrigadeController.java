package Controller;

import Entity.Worker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class BrigadeController {

  @FXML
  private TableView<Worker> TableBrigade;
  @FXML
  private TableColumn<Worker, String> fioColumn;
  @FXML
  private TableColumn<Worker, Integer> brigadeNumColumn;
  @FXML
  private TextField inputNewWorker;
  @FXML
  private ChoiceBox<Integer> choiceBoxOfNumBrigade;
  @FXML
  public void initialize()
  {
    choiceBoxOfNumBrigade.setValue(1);
    choiceBoxOfNumBrigade.setItems(fillChoiceBoxNumOfBrigade(3));
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
    String name = inputNewWorker.getText();
    int numBrigade = choiceBoxOfNumBrigade.getValue();
    System.out.println(name + " " + numBrigade);
  }
}
