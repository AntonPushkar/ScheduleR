package Entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleWrapperForTable
{

  private String brigadeDay;
  private String brigadeNight;
  private String date;
  private Day dayOfSchedule;



  public ScheduleWrapperForTable(Brigade bridageDay, Brigade bridageNigth, LocalDate date, Day day)
  {
    if(bridageDay!=null)
      this.brigadeDay = bridageDay.toString();
    else
      this.brigadeDay = "Выходной";

    if(bridageDay!=null)
      this.brigadeNight = bridageNigth.toString();
    else
      this.brigadeNight = "Выходной";

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.YYYY");
    this.date = date.format(formatter);
    this.dayOfSchedule = day;
  }


  public ScheduleWrapperForTable()
  {

  }

  public String getBrigadeDay() {
    return brigadeDay;
  }

  public String getBrigadeNight() {
    return brigadeNight;
  }

  public String getDate() {
    return date;
  }

  public Day getDayOfSchedule() {
    return dayOfSchedule;
  }

  public void setBrigadeDay(String brigadeDay) {
    this.brigadeDay = brigadeDay;
  }

  public void setBrigadeNight(String brigadeNight) {
    this.brigadeNight = brigadeNight;
  }
  @Override
  public String toString() {
    return "Schedule{" +
        "1 смена='" + brigadeDay + '\'' +
        ", 2 смена='" + brigadeNight + '\'' +
        ", Дата='" + date + '\'' +
        '}';
  }
}
