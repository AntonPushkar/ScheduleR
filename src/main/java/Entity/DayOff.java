package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DayOff
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private LocalDate dateOfDayOff;
  @Transient
  private String date;

  public DayOff(LocalDate date) {
    this.dateOfDayOff = date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.date = date.format(formatter);
  }

  public DayOff()
  {

  }

  public LocalDate getDateOfDayOff() {
    return dateOfDayOff;
  }

  public String getDate() {
    return date;
  }
}
