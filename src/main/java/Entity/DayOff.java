package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
public class DayOff
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private LocalDate date;
  @Transient
  private String formatterDate;

  public DayOff(LocalDate date) {
    this.date = date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.formatterDate = date.format(formatter);

  }

  public DayOff()
  {

  }

  public LocalDate getDate() {
    return date;
  }

  public String getFormattenDate() {
    return formatterDate;
  }
}
