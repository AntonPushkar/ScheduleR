package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Schedule
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String bridageDay;
  private String bridageNigth;
  private String Data;

  public Schedule(String bridageDay, String bridageNigth, String data) {
    this.bridageDay = bridageDay;
    this.bridageNigth = bridageNigth;
    Data = data;
  }

  public Schedule() {
  }

  public String getBridageDay() {
    return bridageDay;
  }

  public String getBridageNigth() {
    return bridageNigth;
  }

  public String getData() {
    return Data;
  }
}
