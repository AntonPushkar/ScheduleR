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

@Entity
public class Brigade
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "numOfBrigade")
  private int numoFBrigade;
  @Column(name = "brigadier")
  @OneToMany
  @JoinTable(name = "team_members", joinColumns = @JoinColumn(name = "Brigade"),
      inverseJoinColumns = @JoinColumn(name = "members"))
  private List<Worker> listOfWorkersInBrigade;

  public Brigade() {
  }

  public Brigade(int numoFBrigade) {
    this.numoFBrigade = numoFBrigade;
  }

  public int getNumoFBrigade() {
    return numoFBrigade;
  }

  public void setNumoFBrigade(int numoFBrigade) {
    this.numoFBrigade = numoFBrigade;
  }

  public List<Worker> getListOfWorkersInBrigade() {
    return listOfWorkersInBrigade;
  }

  public void setListOfWorkersInBrigade(List<Worker> listOfWorkersInBrigade) {
    this.listOfWorkersInBrigade = listOfWorkersInBrigade;
  }
}
