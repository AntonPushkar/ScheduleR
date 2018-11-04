package Entity;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Day {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brigade.class, cascade = CascadeType.ALL)
  private Brigade brigadeDay;
  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Brigade.class, cascade = CascadeType.ALL)
  private Brigade brigadeNigth;

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
    this.brigadeNigth = brigadeNigth;
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
    return brigadeNigth;
  }

  public void setBrigadeNigth(Brigade brigadeNigth) {
    this.brigadeNigth = brigadeNigth;
  }

  public LocalDate getDate() {
    return date;
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
        ", brigadeNigth=" + brigadeNigth +
        ", date='" + date + '\'' +
        ", dayOff=" + dayOff +
        '}';
  }
}


