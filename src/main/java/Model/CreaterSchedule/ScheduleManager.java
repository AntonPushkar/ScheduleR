package Model.CreaterSchedule;

import Entity.Brigade;
import Entity.Day;
import Entity.ScheduleWrapperForTable;
import Model.Manager;
import Model.Managers.DayEntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ScheduleManager implements Manager<ScheduleWrapperForTable>
{
  private ScheduleCreater creater;
  private DayEntityManager dayManager = new DayEntityManager();
  private List<ScheduleWrapperForTable> listScheduleWrapper = new ArrayList<>();
  private static ScheduleManager manager = new ScheduleManager();


  public void createSchedule(LocalDate date)
  {
    if(date!=null)
      creater=new ScheduleCreater(date);
    else
      return;
    creater.createSchedule();
    fillList();
  }

  private void fillList()
  {
      if(!listScheduleWrapper.isEmpty())
        listScheduleWrapper.clear();
      List<Day> listOfDays = creater.getDays();
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

  @Override
  public List<ScheduleWrapperForTable> getListEntities()
  {
    System.out.println(listScheduleWrapper.size());
    return listScheduleWrapper;
  }

  private ScheduleManager()
  {}

  public static ScheduleManager getScheduleManager()
  {
    return manager;
  }
}
