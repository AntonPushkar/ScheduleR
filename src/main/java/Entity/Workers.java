package Entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Worker")
public class Workers
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "secName")
  private String secName;
  @Column(name = "NumBrigade")
  private int numOfBrigade;
  @Column(name = "hashCode")
  private int hashcode;

  public int getHashcode() {
    return hashcode;
  }

  public String getFullName() {
    return fullName;
  }

  @Transient
  private String fullName;


  public Workers(String name, String secName, int numOfBrigade) {
    this.name = name;
    this.secName = secName;
    this.numOfBrigade = numOfBrigade;
    this.fullName = name + " " + secName;
    hashcode = hashCode() * numOfBrigade;

  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Workers workers = (Workers) o;
    return id == workers.id &&
        numOfBrigade == workers.numOfBrigade &&
        Objects.equals(name, workers.name) &&
        Objects.equals(secName, workers.secName) &&
        Objects.equals(fullName, workers.fullName);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, name, secName, numOfBrigade, fullName);
  }

  public Workers()
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
}
