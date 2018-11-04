package Model.Managers;

import DAO.DAODayManager;
import DAO.DAOManager;
import Entity.Day;
import java.util.List;

public class DayManager implements Manager<Day>
{
  private DAOManager manager = new DAODayManager();
  public void insertListDays(List<Day> list)
  {

    for(int i=0; i<list.size(); i++)
    {
      insert(list.get(i));
    }
  }

  @Override
  public void insert(Day day)
  {
    manager.insert(day);
  }

  @Override
  public List<Day> getListOfEntities()
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
