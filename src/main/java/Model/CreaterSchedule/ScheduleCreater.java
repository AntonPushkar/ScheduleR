package Model.CreaterSchedule;

import Entity.Brigade;
import Entity.Day;
import Entity.Schedule;
import Model.CreaterSchedule.util.InformationOfDate;
import Model.Managers.BrigadeManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ScheduleCreater
{
  public void createSchedule()
  {

  }




  private List<Day> generateDays(int daysInMounth, int numOfMonth, int year)
  {

    List<Day> days = new ArrayList<>();
    List<Brigade> brigades= new BrigadeManager().getListOfEntities();
    for(int i=1, j=0; i<=daysInMounth; i++)
    {
      LocalDate date = LocalDate.of(year, numOfMonth, i);
      Brigade dayBrigade;
      Brigade nightBrigade;
      boolean isDayOff=false;
      int dayOfWeek = InformationOfDate.getDayOfWeek(2018, numOfMonth, i);
      if(dayOfWeek == Calendar.SUNDAY)
      {
        isDayOff = true;
        days.add(new Day(null, null, date, isDayOff));
        continue;
      }
      if(j==brigades.size()) j=0;
      dayBrigade = brigades.get(j);
      j++;
      if(j==brigades.size()) j=0;
      nightBrigade = brigades.get(j);
      j++;
      days.add(new Day(dayBrigade, nightBrigade, date, isDayOff));
    }
    return days;
  }

  public List<Schedule> generateSchedule(List<Day> days)
  {
    List<Schedule> schedules = new ArrayList<>();
    Date date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    for(int i=0; i<days.size(); i++)
    {
      Day day = days.get(i);
      Brigade BrigadeDay=days.get(i).getBrigadeDay();
      Brigade BrigadeNight=days.get(i).getBrigadeNigth();
      String strDate = formatter.format(days.get(i).getDate());
      if(days.get(i).isDayOff())
      {
        schedules.add(new Schedule("Выходной", "Выходной", strDate));
        continue;
      }

      schedules.add(new Schedule(BrigadeDay.toString(),BrigadeNight.toString(),strDate));
    }
    return schedules;
  }



  public static void main(String[] args)
  {
    int year = 2018;
    int month = 11;
    int dayMonth = InformationOfDate.getNumOfDayInMonth(year, month);
    List<Day> days = new ScheduleCreater().generateDays(dayMonth, month, year);
    List<Schedule> schedules = new ScheduleCreater().generateSchedule(days);

    for(int i=0; i<schedules.size(); i++)
    {
      System.out.println(schedules.get(i));
    }



  }
}
