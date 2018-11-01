package Model.CreaterSchedule.util;

import java.time.YearMonth;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class InformationOfDate
{

  private static YearMonth yearMonth;
  public static int getNumOfDayInMonth(int year, int month)
  {
    yearMonth = YearMonth.of(year, month);
    return yearMonth.lengthOfMonth();
  }

  public static int getNumOfMonth(int year, int month)
  {
    yearMonth = YearMonth.of(year, month);
    return yearMonth.lengthOfMonth();
  }

  public static int getDayOfWeek(int year, int mounth, int day) {
    Calendar calendar = new GregorianCalendar();
    calendar.set(year, mounth - 1, day);
    int DayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    return DayOfWeek;
  }

}
