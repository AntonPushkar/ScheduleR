package Entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Schedule
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne
  private Brigade bridageDay;
  @ManyToOne
  private Brigade bridageNigth;
  private LocalDate date;

  public Schedule(Brigade bridageDay, Brigade bridageNigth, LocalDate date) {
    this.bridageDay = bridageDay;
    this.bridageNigth = bridageNigth;
    this.date = date;
  }

  public Schedule() {
  }

  public Brigade getBridageDay() {
    return bridageDay;
  }

  public Brigade getBridageNigth() {
    return bridageNigth;
  }

  public LocalDate getDate() {
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
