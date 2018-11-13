package Model.Managers;

import DAO.DAODayManager;
import DAO.DAOManager;
import Entity.Day;
import java.util.List;

public class DayEntityManager implements EntityManager<Day>
{
  private DAOManager<Day> manager = new DAODayManager();
  public void insertListDays(List<Day> list)
  {

    for (Day aList : list) {
      insert(aList);
    }
  }

  @Override
  public void insert(Day day)
  {
    manager.insert(day);
  }

  @Override
  public List<Day> getListEntities()
  {
    return manager.get();
  }

  @Override
  public void remove(Day day)
  {
    manager.remove(day);
  }

  @Override
  public void update(Day day)
  {
    manager.update(day);
  }
}
