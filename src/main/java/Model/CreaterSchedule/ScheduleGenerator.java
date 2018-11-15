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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
*
*
* This class serve for generate schedule
*
*/
public class ScheduleGenerator
{

  private int numDayOfMonth = 1;
  private int year;
  private int month;
  private int daysInMonth;
  private List<Day> days = new ArrayList<>();
  private LocalDate date;



  public ScheduleGenerator(LocalDate date) {
    this.year = date.getYear();
    this.month = date.getMonthValue();
    this.daysInMonth = date.lengthOfMonth();
    this.date = date;
  }

  /*because a schedule can start not from monday, created part of schedule in order to found out last data.
   * If we have data of last month we don't create part of schedule */
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
      generateSchedule();
      System.out.println((days.size()));
    }
    return days;
  }


  /*We find the last monday in month, set the start brigade and generate schedule.
   This schedule nowhere shows and nowhere recorded*/
  private void createPartOfSchedule()
  {
    int lastMonday = InformationOfDate.getLastMondayInMonth(year, month - 1);
    LocalDate date = LocalDate.of(year, month-1, lastMonday);
    ScheduleGenerator creater = new ScheduleGenerator(date);
    creater.setNumDayOfMonth(lastMonday);
    creater.createSchedule();
    generateSchedule();
  }



  /*method of generateSchedule
  * Get the list of brigades, list days off
  * two HashMaps serve for keeping the information about canceled shifts. 1st or 2nd work shift.
  * Check that new month started from monday or not. Read data of last month from properties.
  * Then check is this date day off or not. Then check that do this day cancled work shifts or not.
  * If yes then instead reference on brigade, put in day ****null**** data
  *
  * */
  public List<Day> generateSchedule()
  {
    List<Brigade> brigades= new BrigadeEntityManager().getListEntities();
    Brigade dayBrigade;
    Brigade nightBrigade;
    List<DayOff> daysOff = new DayOffEntityManager().getListEntities();
    utilForScheduleCreater utilForSchedule = new utilForScheduleCreater();
    Map<LocalDate, Boolean> mapOfCancelFirstChange = new HashMap<>();
    Map<LocalDate, Boolean> mapOfCancelSecondChange = new HashMap<>();
    utilForSchedule.fillMap(mapOfCancelFirstChange, mapOfCancelSecondChange, daysOff);
    for(; numDayOfMonth<=daysInMonth; numDayOfMonth++)
    {
      LocalDate scheduleDate = LocalDate.of(year, month, numDayOfMonth);
      boolean isDayOff=utilForSchedule.isDayOff(daysOff, scheduleDate);
      int dayOfWeek = scheduleDate.getDayOfWeek().getValue();
      if((dayOfWeek == DayOfWeek.MONDAY.getValue() ||
          dayOfWeek != DayOfWeek.MONDAY.getValue() && numDayOfMonth == 1))
        utilForSchedule.setNumBrigade(utilForSchedule.getNumBrigadeFromProperties(scheduleDate, numDayOfMonth));
      boolean isCancelFirstChange=false;
      boolean isCancelSecondChange=false;
      if(isDayOff)
      {
        utilForSchedule.addDaysOffToSchedule(scheduleDate);
        continue;
      }

      if(mapOfCancelFirstChange.containsKey(scheduleDate))
        isCancelFirstChange = mapOfCancelFirstChange.get(scheduleDate);
      if(mapOfCancelSecondChange.containsKey(scheduleDate))
        isCancelSecondChange = mapOfCancelSecondChange.get(scheduleDate);


      if(isCancelFirstChange)
        dayBrigade = null;
      else dayBrigade = brigades.get(utilForSchedule.getNextBrigade());
      if(isCancelSecondChange)
        nightBrigade = null;
      else nightBrigade = brigades.get(utilForSchedule.getNextBrigade());


      days.add(new Day(dayBrigade, nightBrigade, scheduleDate,
          isDayOff, isCancelFirstChange, isCancelSecondChange));
      utilForSchedule.writeDataSchedule(scheduleDate, numDayOfMonth, dayBrigade, nightBrigade);

    }
    numDayOfMonth=1;
    return days;
  }


  /*
  *
  *Util class for generator of schedule.
  */
  public class utilForScheduleCreater
  {

    /*This method returned the next brigade for schedule*/
    private int numBrigade;
    public void setNumBrigade(int numBrigade)
    {
      if(numBrigade==new BrigadeEntityManager().getListEntities().size())
        numBrigade = 0;
      this.numBrigade = numBrigade;
    }
    public int getNextBrigade()
    {
      int temp = numBrigade;
      setNumBrigade(numBrigade+1);
      return temp;
    }
    /*read data of last month from properties */
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
      //in the end there are true and true because if dayOff is true then first shift and second shift also
      // canceled
      days.add(new Day(null, null, date,dayOff, true, true));
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

    /*check that is day day off or not*/
    public boolean isDayOff(List<DayOff> list, LocalDate date)
    {
      int weekDay = date.getDayOfWeek().getValue();
      for (DayOff aList : list) {
        LocalDate dayOff = aList.getDate();
        if (dayOff.equals(date))
        {
          if(aList.isDayOff())
            return true;
        }
      }
      if(weekDay == DayOfWeek.SUNDAY.getValue())
        return true;
      return false;
    }
    /*fill in the abolition of work shifts*/
    private void fillMap(Map firstShifts, Map secondShifts, List<DayOff> list)
    {
      for(DayOff x : list)
      {
        firstShifts.put(x.getDate(), x.isCancelFirstShift());
        secondShifts.put(x.getDate(), x.isCancelSecondShift());
      }
    }


  }



  public void setNumDayOfMonth(int numDayOfMonth) {
    this.numDayOfMonth = numDayOfMonth;
  }

  public List<Day> getDays() {
    return days;
  }
}
