package Model.CreaterSchedule;

import Controller.DialogsWindow.DialogueMainWindow;
import Entity.Brigade;
import Entity.Day;
import Entity.DayOff;
import Model.CreaterSchedule.Validators.ValidateInitialData;
import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.CreaterSchedule.util.InformationOfDate;
import Model.FieldsOfProperties;
import Model.Managers.BrigadeEntityManager;
import Model.Managers.DayOffEntityManager;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/*THIS CLASS NEEDS THE EXPLANATION!!! ANTON, DON'T FORGET WRITE COMMENT FOR THIS CODE!!!!*/
public class ScheduleCreater
{

  private int numDayOfMonth = 1;
  private int year;
  private int month;
  private int daysInMonth;
  private List<Day> days = new ArrayList<>();
  private LocalDate date;



  public ScheduleCreater(LocalDate date) {
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.daysInMonth = date.lengthOfMonth();
    this.date = date;
  }


  public  List<Day> createSchedule() {
    if(!days.isEmpty())
    {
      days.clear();
    }
    if ((date.getDayOfWeek().getValue() != DayOfWeek.MONDAY.getValue())
        && !ValidateInitialData.validateFirstBrigadeOfLastWeek())
    {
     createPartOfSchedule();
    }
    else
    {
      if(!ValidateInitialData.validateLastBrigadeInMonth())
        DialogueMainWindow.getInitialBrigade();
      generateDays();
      System.out.println((days.size()));
    }
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
    List<Brigade> brigades= new BrigadeEntityManager().getListEntities();
    int firstBrigadeOfLastWeek;
    Brigade dayBrigade;
    Brigade nightBrigade;
    List<DayOff> daysOff = new DayOffEntityManager().getListEntities();
    NumBrigade numBrigade = new NumBrigade();
    for(; numDayOfMonth<=daysInMonth; numDayOfMonth++)
    {
      LocalDate scheduleDate = LocalDate.of(year, month, numDayOfMonth);
      boolean isDayOff=isDayOff(daysOff, scheduleDate);
      int dayOfWeek = scheduleDate.getDayOfWeek().getValue();
      if((dayOfWeek == DayOfWeek.MONDAY.getValue() ||
          dayOfWeek != DayOfWeek.MONDAY.getValue() && numDayOfMonth == 1))
        numBrigade.setNumBrigade(getNumBrigadeFromProperties(scheduleDate, numDayOfMonth));
      if(isDayOff)
      {
        addDaysOffToSchedule(scheduleDate);
        continue;
      }
      dayBrigade = brigades.get(numBrigade.getNumBrigade());
      nightBrigade = brigades.get(numBrigade.getNumBrigade());
      days.add(new Day(dayBrigade, nightBrigade, scheduleDate, isDayOff));
      writeDataSchedule(scheduleDate, numDayOfMonth, dayBrigade, nightBrigade);

    }
    numDayOfMonth=1;
    return days;
  }



  public String readScheduleProperty(FieldsOfProperties e)
  {
    return DataScheduleProperty.readProperty(e.toString());
  }

  public void writeProperty(FieldsOfProperties e, String value)
  {
    DataScheduleProperty.writeProperty(e.toString(), value);
  }

  public void writeDataSchedule(LocalDate date, int numDayOfMonth, Brigade day, Brigade night)
  {
    int dayOfWeek = date.getDayOfWeek().getValue();
    if(dayOfWeek==DayOfWeek.MONDAY.getValue())
      writeProperty(FieldsOfProperties.FIRST_BRIGADE_LAST_WEEK, String.valueOf(day.getNumOfBrigade()));
    else if(dayOfWeek==Calendar.SATURDAY)
      writeProperty(FieldsOfProperties.LAST_BRIGADE_IN_WEEK, String.valueOf(night.getNumOfBrigade()));
    if(numDayOfMonth ==daysInMonth)
    {
      writeProperty(FieldsOfProperties.LAST_BRIGADE_IN_MONTH, String.valueOf(night.getNumOfBrigade()));
      writeProperty(FieldsOfProperties.MONTH_OF_LAST_SCHEDULE, String.valueOf(date.getMonthValue()));
    }
  }

  public class NumBrigade
  {
    private int numBrigade;
    public void setNumBrigade(int numBrigade)
    {
      if(numBrigade==new BrigadeEntityManager().getListEntities().size())
        numBrigade = 0;
      this.numBrigade = numBrigade;
    }
    private int getNumBrigade()
    {
      int temp = numBrigade;
      setNumBrigade(numBrigade+1);
      return temp;
    }
  }


  public int getNumBrigadeFromProperties(LocalDate date, int dayOfMonth)
  {
    int dayOfWeek = date.getDayOfWeek().getValue();
    int currentBrigade=0;
    if(dayOfWeek == DayOfWeek.MONDAY.getValue())
    {
      currentBrigade = Integer.parseInt(readScheduleProperty(FieldsOfProperties.FIRST_BRIGADE_LAST_WEEK));
      return currentBrigade;
    }
    else if (dayOfWeek != DayOfWeek.MONDAY.getValue() && dayOfMonth == 1)
    {
      currentBrigade=Integer.parseInt(readScheduleProperty(FieldsOfProperties.LAST_BRIGADE_IN_MONTH));
    }
    return currentBrigade;
  }


  public void addDaysOffToSchedule(LocalDate date)
  {
    boolean dayOff = true;
    List<DayOff> daysOff = new DayOffEntityManager().getListEntities();
    days.add(new Day(null, null, date,dayOff));
  }


  public boolean isDayOff(List<DayOff> list, LocalDate date)
  {
    int weekDay = date.getDayOfWeek().getValue();
    for (DayOff aList : list) {
      LocalDate dayOff = aList.getDateOfDayOff();
      if (dayOff.equals(date)) {
        return true;
      }
    }
    if(weekDay == DayOfWeek.SUNDAY.getValue())
      return true;
    return false;
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

  public List<Day> getDays() {
    return days;
  }
}
