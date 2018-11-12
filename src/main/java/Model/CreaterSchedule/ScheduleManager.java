package Model.CreaterSchedule;

import Entity.Brigade;
import Entity.Day;
import Entity.ScheduleWrapperForTable;
import Model.Managers.DayManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager
{
  private ScheduleCreater creater;
  private LocalDate date;
  private DayManager dayManager = new DayManager();

  public List<ScheduleWrapperForTable> getSchedule()
  {
    List<Day> listOfDays;
    List<ScheduleWrapperForTable> listScheduleWrapper = new ArrayList<>();
    if(creater!=null) {
      listOfDays = creater.getDays();
      dayManager.insertListDays(listOfDays);
      for (int i = 0; i < listOfDays.size(); i++)
      {
        Brigade brDay = listOfDays.get(i).getBrigadeDay();
        Brigade brNight = listOfDays.get(i).getBrigadeNight();
        LocalDate date = listOfDays.get(i).getDate();
        Day day = listOfDays.get(i);
        listScheduleWrapper.add(new ScheduleWrapperForTable(brDay, brNight, date, day));
      }
    }
    else return listScheduleWrapper;

    return listScheduleWrapper;
  }

  public ScheduleManager(LocalDate date)
  {
    this.date = date;
  }

  public void createSchedule()
  {
    if(date!=null)
      creater=new ScheduleCreater(this.date);
    else
      return;
    creater.createSchedule();
  }
}
