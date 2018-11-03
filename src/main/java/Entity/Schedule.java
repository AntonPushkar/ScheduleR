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
  private String date;

  public Schedule(String bridageDay, String bridageNigth, String date) {
    this.bridageDay = bridageDay;
    this.bridageNigth = bridageNigth;
    this.date = date;
  }

  public Schedule() {
  }

  public String getBridageDay() {
    return bridageDay;
  }

  public String getBridageNigth() {
    return bridageNigth;
  }

  public String getDate() {
    return date;
  }

  @Override
  public String toString() {
    return "Schedule{" +
        "1 смена='" + bridageDay + '\'' +
        ", 2 смена='" + bridageNigth + '\'' +
        ", Дата='" + date + '\'' +
        '}';
  }
}
