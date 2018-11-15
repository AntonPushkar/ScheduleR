package Model;

import Entity.Day;
import Entity.DayOff;
import Model.Managers.DayEntityManager;
import Model.Managers.DayOffEntityManager;
import java.time.LocalDate;
import java.util.List;

public class EditCreateDaysOff
{
  private static final DayOffEntityManager dayOffManager = new DayOffEntityManager();
  private static List<DayOff> daysOff;
  private static final DayEntityManager dayManager = new DayEntityManager();
  public static void analyzeDayOff(Day day)
  {
    if(day.isCancelSecondShift() && day.isCancelSecondShift())
      day.setDayOff(true);
    dayManager.update(day);
    daysOff = dayOffManager.getListEntities();
    boolean isCancelFirstShift = day.isCancelFirstShift();
    boolean isCancelSecondShift = day.isCancelSecondShift();
    boolean isDayOff = day.isDayOff();
    LocalDate date = day.getDate();
    DayOff dayOff;
    if(daysOff.isEmpty()) {
      dayOff = new DayOff(date,isDayOff, isCancelFirstShift, isCancelSecondShift);
      dayOffManager.insert(dayOff);
    }
    else
    {
      dayOff = isExistDayOff(day.getDate());
      if(dayOff!=null)
      {
        dayOff.setCancelFirstShift(isCancelFirstShift);
        dayOff.setCancelFirstShift(isCancelSecondShift);
        dayOff.setDayOff(isDayOff);
        dayOffManager.update(dayOff);
      }
      else
      {
        dayOff = new DayOff(date, isDayOff, isCancelFirstShift, isCancelSecondShift);
        dayOffManager.insert(dayOff);
      }

    }


  }

  private static DayOff isExistDayOff(LocalDate date)
  {
    for(DayOff x : daysOff)
    {
      if(x.getDate().equals(date))
        return x;
    }
    return null;
  }


}
