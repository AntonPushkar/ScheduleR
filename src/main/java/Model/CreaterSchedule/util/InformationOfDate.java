package Model.CreaterSchedule.util;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InformationOfDate
{

  public static int getNumOfDayInMonth(int year, int month)
  {
    YearMonth yearMonth = YearMonth.of(year, month);
    return yearMonth.lengthOfMonth();
  }

  public static int getDayOfWeek(int year, int month, int day) {
    Calendar calendar = new GregorianCalendar();
    calendar.set(year, month - 1, day);
    return calendar.get(Calendar.DAY_OF_WEEK);
  }

  public static int getLastMondayInMonth(int year, int month)
  {
    int monday = 0;
    for(int i=getNumOfDayInMonth(year,month); i>=1; i--)
    {
      if(getDayOfWeek(year, month, i)==Calendar.MONDAY)
      {
        monday = i;
        break;
      }
    }
    return monday;
  }
}
