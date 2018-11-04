package Model.CreaterSchedule;

import Controller.DialogsWindow.DialogueMainWindow;
import Entity.Brigade;
import Entity.Day;
import Entity.Schedule;
import Model.CreaterSchedule.Validators.ValidateInitialData;
import Model.CreaterSchedule.util.InformationOfDate;
import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.Managers.BrigadeManager;
import Model.Managers.DayManager;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


/*THIS CLASS NEEDS THE EXPLANATIONS!!! ANTON, DON'T FORGET WRITE COMMENT FOR THIS CODE!!!!*/
public class ScheduleCreater
{

  private DayManager dayManager = new DayManager();

  private int numDayOfMonth = 1;
  private int year;
  private int month;
  private int daysInMonth;
  private List<Day> days = new ArrayList<>();



  public ScheduleCreater(LocalDate date) {
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.daysInMonth = date.lengthOfMonth();
  }


  public  List<Day> createSchedule() {
    if(!days.isEmpty())
    {
      days.clear();
    }
    if ((InformationOfDate.getDayOfWeek(year, month, 1)) != Calendar.MONDAY
        && !ValidateInitialData.validateFirstBrigadeOfLastWeek())
    {
     createPartOfSchedule();
    }
    if(!ValidateInitialData.validateLastBrigadeInMonth())
      DialogueMainWindow.getInitialBrigade();
    generateDays();
    dayManager.insertListDays(days);
    return days;
  }

  private void createPartOfSchedule()
  {
    int lastMonday = InformationOfDate.getLastMondayInMonth(year, month - 1);
    LocalDate date = LocalDate.of(year, month-1, lastMonday);
    ScheduleCreater creater = new ScheduleCreater(date);
    creater.setNumDayOfMonth(lastMonday);
    creater.createSchedule();
    generateDays();
  }



  /*Here will be the explanations of code below. Anton, DON'T FORGET WRITE IT!!!!!!!!!!!!!!!
   * Because you don't understand this code in a future!!!!*/
  public List<Day> generateDays()
  {
    List<Brigade> brigades= new BrigadeManager().getListOfEntities();
    int firstBrigadeOfLastWeek = -1;
    Brigade dayBrigade;
    Brigade nightBrigade;
    for(int numBrigade =0; numDayOfMonth <=daysInMonth; numDayOfMonth++)
    {
      LocalDate date = LocalDate.of(year, month, numDayOfMonth);
      boolean isDayOff=false;
      int dayOfWeek = InformationOfDate.getDayOfWeek(year, month, numDayOfMonth);

      if(dayOfWeek == Calendar.MONDAY) {
        firstBrigadeOfLastWeek = Integer
            .parseInt(DataScheduleProperty.readProperty("firstBrigadeOfLastWeek"));
        numBrigade = firstBrigadeOfLastWeek;
      }
      else if(dayOfWeek != Calendar.MONDAY && numDayOfMonth ==1)
      {
        numBrigade=Integer.parseInt(DataScheduleProperty.readProperty("lastBrigadeInMonth"));
      }

      if(dayOfWeek == Calendar.SUNDAY)
      {
        isDayOff = true;
        days.add(new Day(null, null, date, isDayOff));
        continue;
      }
      if(numBrigade ==brigades.size() || numBrigade >brigades.size()) numBrigade =0;
      dayBrigade = brigades.get(numBrigade);
      numBrigade++;

      if(numBrigade ==brigades.size() || numBrigade >brigades.size()) numBrigade =0;
      nightBrigade = brigades.get(numBrigade);
      numBrigade++;

      days.add(new Day(dayBrigade, nightBrigade, date, isDayOff));

      if(dayOfWeek==Calendar.MONDAY)
        DataScheduleProperty.writeProperty("firstBrigadeOfLastWeek",
            String.valueOf(dayBrigade.getNumOfBrigade()));

      if(dayOfWeek==Calendar.SATURDAY)
        DataScheduleProperty.writeProperty("lastBrigadeInWeek",
            String.valueOf(nightBrigade.getNumOfBrigade()));

      if(numDayOfMonth ==daysInMonth)
      {
        DataScheduleProperty.writeProperty("lastBrigadeInMonth",
            String.valueOf(nightBrigade.getNumOfBrigade()));
        DataScheduleProperty.writeProperty("MonthOfLastSchedule", String.valueOf(month));
      }
    }
    numDayOfMonth=1;
    return days;
  }




  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  public int getMonth() {
    return month;
  }

  public void setMonth(int month) {
    this.month = month;
  }

  public int getNumDayOfMonth() {
    return numDayOfMonth;
  }

  public void setNumDayOfMonth(int numDayOfMonth) {
    this.numDayOfMonth = numDayOfMonth;
  }

}
