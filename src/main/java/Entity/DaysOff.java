package Entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DaysOff
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private LocalDate date;

  public DaysOff(LocalDate date) {
    this.date = date;
  }

  public DaysOff()
  {

  }

  public LocalDate getDate() {
    return date;
  }
}
