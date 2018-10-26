package Entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Brigade
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "numOfBrigade", unique = true)
  private int numOfBrigade;
  @OneToOne
  Worker brigadier;
  @OneToMany
  @JoinTable(name = "team_members", joinColumns = @JoinColumn(name = "Brigade"),
      inverseJoinColumns = @JoinColumn(name = "members"))
  private List<Worker> listOfWorkersInBrigade;

  public Worker getBrigadier() {
    return brigadier;
  }

  public void setBrigadier(Worker brigadier) {
    this.brigadier = brigadier;
  }

  public Brigade() {
  }

  public Brigade(int numOfBrigade, Worker brigadier,
      List<Worker> listOfWorkersInBrigade) {
    this.numOfBrigade = numOfBrigade;
    this.brigadier = brigadier;
    this.listOfWorkersInBrigade = listOfWorkersInBrigade;
  }

  public Brigade(int numOfBrigade) {
    this.numOfBrigade = numOfBrigade;
  }

  public int getNumOfBrigade() {
    return numOfBrigade;
  }

  public void setNumOfBrigade(int numoFBrigade) {
    this.numOfBrigade = numoFBrigade;
  }

  public List<Worker> getListOfWorkersInBrigade() {
    return listOfWorkersInBrigade;
  }

  public void addWorker(Worker worker) {
    this.listOfWorkersInBrigade.add(worker);
  }
}
