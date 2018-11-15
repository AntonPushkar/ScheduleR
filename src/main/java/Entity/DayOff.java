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
  private LocalDate date;
  @Transient
  private String strDate;
  private boolean cancelFirstShift = false;
  private boolean cancelSecondShift = false;
  private boolean isDayOff = false;



  public DayOff(LocalDate date, boolean isDayOff, boolean cancelFirstShift, boolean cancelSecondChange) {
    this.date = date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.strDate = date.format(formatter);
    this.cancelFirstShift = cancelFirstShift;
    this.cancelSecondShift = cancelSecondChange;
    this.isDayOff = isDayOff;
  }

  public DayOff()
  {

  }

  public LocalDate getDate() {
    return date;
  }

  public String getStrDate() {
    return strDate;
  }

  public boolean isCancelFirstShift() {
    return cancelFirstShift;
  }

  public void setCancelFirstShift(boolean cancelFirstChange) {
    this.cancelFirstShift = cancelFirstChange;
  }

  public boolean isCancelSecondShift() {
    return cancelSecondShift;
  }

  public void setCancelSecondShift(boolean cancelSecondChange) {
    this.cancelSecondShift = cancelSecondChange;
  }

  public boolean isDayOff() {
    return isDayOff;
  }

  public void setDayOff(boolean dayOff) {
    isDayOff = dayOff;
  }
}

