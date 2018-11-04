package Entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
  @OneToMany(fetch = FetchType.EAGER, targetEntity = Worker.class, cascade = CascadeType.ALL)
  private List<Worker> listOfWorkersInBrigade = new ArrayList<>();
  public Worker getBrigadier() {
    return brigadier;
  }

  public void setBrigadier(Worker brigadier) {
    this.brigadier = brigadier;
  }

  public Brigade() {
  }

  public Brigade(int numOfBrigade, Worker brigadier)
  {
    this.numOfBrigade = numOfBrigade;
    this.brigadier = brigadier;
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

  @Override
  public String toString()
  {
    return "Бригада № "+ numOfBrigade;
  }
}
