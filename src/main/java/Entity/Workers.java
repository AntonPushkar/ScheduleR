package Entity;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Worker")
public class Workers
{
  @Id
  private int id;
  @Column(name = "name")
  private String name;
  @Column(name = "secName")
  private String secName;
  @Column(name = "NumBrigade")
  private int numOfBrigade;

  @Transient
  private SimpleStringProperty nameForView;
  @Transient
  private SimpleStringProperty secNameForView;
  @Transient
  private SimpleIntegerProperty numOfBrigadeForView;

  public Worker(String name, String secName, int numOfBrigade) {
    this.name = name;
    this.secName = secName;
    this.numOfBrigade = numOfBrigade;
  }

  public Worker() {
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
