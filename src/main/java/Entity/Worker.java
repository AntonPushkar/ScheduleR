package Entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Worker")
public class Worker extends Employee
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "secName")
  private String secName;
  @Column(name = "NumBrigade")
  private int numOfBrigade;
  @Column(name = "personnelNum")
  private int PersonnelNum;
  @Column(name = "isBrigadier")
  private boolean brigadier;



  public String getFullName() {
    return fullName;
  }

  @Transient
  private String fullName;


  public Worker(String name, String secName, int numOfBrigade, int PersonnelNum) {
    this.name = name;
    this.secName = secName;
    this.numOfBrigade = numOfBrigade;
    this.PersonnelNum = PersonnelNum;
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
    Entity.Worker worker = (Entity.Worker) o;
    return id == worker.id &&
        numOfBrigade == worker.numOfBrigade &&
        Objects.equals(name, worker.name) &&
        Objects.equals(secName, worker.secName) &&
        Objects.equals(fullName, worker.fullName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, secName, numOfBrigade, fullName);
  }

  public Worker()
  {

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecName() {
    return secName;
  }

  public void setSecName(String secName) {
    this.secName = secName;
  }

  public int getNumOfBrigade() {
    return numOfBrigade;
  }

  public void setNumOfBrigade(int numOfBrigade) {
    this.numOfBrigade = numOfBrigade;
  }
  public int getPersonnelNum() {
    return PersonnelNum;
  }
}
