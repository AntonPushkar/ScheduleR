package Model.CreaterSchedule.util;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InformationOfDate
{

  private static int getNumOfDayInMonth(int year, int month)
  {
    YearMonth yearMonth = YearMonth.of(year, month);
    return yearMonth.lengthOfMonth();
  }

  private static int getDayOfWeek(int year, int numMonth, int day) {
    Calendar calendar = new GregorianCalendar();
    numMonth = numMonth-1; //because in calendar month started from 0
    //noinspection MagicConstant
    calendar.set(year, numMonth , day);
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
