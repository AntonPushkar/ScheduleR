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
  private ScheduleGenerator scheduleGenerator;
  private final DayEntityManager dayManager = new DayEntityManager();
  private final List<ScheduleWrapperForTable> listScheduleWrapper = new ArrayList<>();
  private static final ScheduleManager manager = new ScheduleManager();


  public void createSchedule(LocalDate date)
  {
    if(date!=null)
      scheduleGenerator =new ScheduleGenerator(date);
    else
      return;
    scheduleGenerator.createSchedule();
    fillList();
  }

  private void fillList()
  {
      if(!listScheduleWrapper.isEmpty())
        listScheduleWrapper.clear();
      List<Day> listOfDays = scheduleGenerator.getDays();
      dayManager.insertListDays(listOfDays);
    for (Day dayX : listOfDays) {
      Brigade brDay = dayX.getBrigadeDay();
      Brigade brNight = dayX.getBrigadeNight();
      LocalDate date = dayX.getDate();
      Day day = dayX;
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
