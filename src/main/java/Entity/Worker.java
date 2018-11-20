package Entity;

import Model.Managers.BrigadeEntityManager;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Worker")
public class Worker extends Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
    @Column(name = "NumBrigade")
    private int numOfBrigade;
    @Column(name = "isBrigadier")
    private boolean brigadier=false;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brigade.class, cascade = CascadeType.ALL)
    private Brigade brigade;

    @Transient
    private String fullName;


    public Worker(String name, String secName, int numOfBrigade, String PersonnelNum, boolean isBrigadier)
    {
      super(name, secName, PersonnelNum);
      Brigade brigade = new BrigadeEntityManager().getListEntities().get(numOfBrigade-1);
      this.numOfBrigade = numOfBrigade;
      this.brigadier = isBrigadier;
      this.fullName = name + " " + secName;
      this.brigade = brigade;
      //brigade.addWorker(this);
    }



  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Worker worker = (Worker) o;
    return (numOfBrigade==worker.numOfBrigade)
        && (brigadier==worker.brigadier)
        && (super.getName().equals(worker.getName())) && (super.getSecName().equals(worker.getSecName()))
        && (super.getPersonnelNum().equals(worker.getPersonnelNum()));
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.getName(), super.getSecName(), numOfBrigade, fullName);
  }

  public Worker()
  {

  }

  public String getFullName() {
    return fullName;
  }

  public String getName() {
    return super.getName();
  }

  public void setName(String name) {
    super.setName(name);
  }

  public String getSecName() {
    return super.getSecName();
  }

  public void setSecName(String secName) {
    super.setSecName(secName);
  }

  public int getNumOfBrigade() {
    return numOfBrigade;
  }

  public void setNumOfBrigade(int numOfBrigade) {
    this.numOfBrigade = numOfBrigade;
  }
  public String getPersonnelNum() {
    return super.getPersonnelNum();
  }

  public boolean isBrigadier() {
    return brigadier;
  }

  public void setBrigadier(boolean brigadier) {
    this.brigadier = brigadier;
  }

  public Brigade getBrigade() {
    return brigade;
  }

  public void setBrigade(Brigade brigade) {
    this.brigade = brigade;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  @Override
  public String toString() {
    return "Worker{" +
        "id=" + id +
        ", numOfBrigade=" + numOfBrigade +
        ", brigadier=" + brigadier +
        ", brigade=" + brigade +
        ", fullName='" + fullName + '\'' +
        '}';
  }
}
