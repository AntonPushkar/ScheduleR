package Entity;

import java.util.Objects;
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
//  @Column(name = "name")
//  private String name;
//  @Column(name = "secName")
//  private String secName;
  @Column(name = "NumBrigade")
  private int numOfBrigade;
//  @Column(name = "personnelNum")
//  private String PersonnelNum;
  @Column(name = "isBrigadier")
  private boolean brigadier;
  @ManyToOne(fetch = FetchType.EAGER)
  private Brigade brigade;



  public String getFullName() {
    return fullName;
  }

  @Transient
  private String fullName;


  public Worker(String name, String secName, int numOfBrigade, String PersonnelNum) {
    super(name, secName, PersonnelNum);
    this.numOfBrigade = numOfBrigade;
    this.fullName = name + " " + secName;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Worker worker = (Worker) o;

    return
        numOfBrigade == worker.numOfBrigade &&
        Objects.equals(super.getName(), worker.getName()) &&
        Objects.equals(super.getSecName(), worker.getName()) &&
        Objects.equals(fullName, worker.fullName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(super.getName(), super.getSecName(), numOfBrigade, fullName);
  }

  public Worker()
  {

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
}
