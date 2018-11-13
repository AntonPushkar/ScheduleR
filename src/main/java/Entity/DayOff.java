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
  private boolean cancelFirstShift = false;
  private boolean cancelSeocndShift = false;
  private boolean isDayOff = false;



  public DayOff(LocalDate date, boolean isDayOff, boolean cancelFirstShift, boolean cancelSeocndChange) {
    this.dateOfDayOff = date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.date = date.format(formatter);
    this.cancelFirstShift = cancelFirstShift;
    this.cancelSeocndShift =cancelSeocndChange;
    this.isDayOff = isDayOff;
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

  public boolean isCancelFirstShift() {
    return cancelFirstShift;
  }

  public void setCancelFirstShift(boolean cancelFirstChange) {
    this.cancelFirstShift = cancelFirstChange;
  }

  public boolean isCancelSeocndShift() {
    return cancelSeocndShift;
  }

  public void setCancelSeocndShift(boolean cancelSeocndChange) {
    this.cancelSeocndShift = cancelSeocndChange;
  }

  public boolean isDayOff() {
    return isDayOff;
  }

  public void setDayOff(boolean dayOff) {
    isDayOff = dayOff;
  }
}

