package Entity;



import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;



@Entity
public class Day {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brigade.class, cascade = CascadeType.ALL)
  private Brigade brigadeDay;
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brigade.class, cascade = CascadeType.ALL)
  private Brigade brigadeNight;

  private String formatterDate;
  private LocalDate date;

  private boolean dayOff = false;
  private boolean cancelFirstShift = false;
  private boolean cancelSecondShift = false;



  public Day()
  {

  }

  public Day(Brigade brigadeDay, Brigade brigadeNigth, LocalDate date, boolean dayOff) {
    this.brigadeDay = brigadeDay;
    this.brigadeNight = brigadeNigth;
    this.date = date;
    this.dayOff = dayOff;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.formatterDate = date.format(formatter);
  }

  public Brigade getBrigadeDay() {
    return brigadeDay;
  }

  public void setBrigadeDay(Brigade brigadeDay) {
    this.brigadeDay = brigadeDay;
  }

  public Brigade getBrigadeNight() {
    return brigadeNight;
  }

  public void setBrigadeNigth(Brigade brigadeNigth) {
    this.brigadeNight = brigadeNigth;
  }

  public boolean isDayOff() {
    return dayOff;
  }

  public void setDayOff(boolean dayOff) {
    this.dayOff = dayOff;
  }

  public boolean isCancelFirstShift() {
    return cancelFirstShift;
  }

  public void setCancelFirstShift(boolean cancelFirstShift) {
    this.cancelFirstShift = cancelFirstShift;
  }

  public boolean isCancelSecondShift() {
    return cancelSecondShift;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setCancelSecondShift(boolean cancelSecondShift) {
    this.cancelSecondShift = cancelSecondShift;
  }
  public String getFormatterDate() {
    return formatterDate;
  }

  @Override
  public String toString() {
    return "Day{" +
        "brigadeDay=" + brigadeDay +
        ", brigadeNigth=" + brigadeNight +
        ", date='" + date + '\'' +
        ", dayOff=" + dayOff +
        '}';
  }
}


