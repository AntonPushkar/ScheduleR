package Model.Managers;

import DAO.DAODaysOffManager;
import DAO.DAOManager;
import Entity.DayOff;
import java.util.List;

public class DayOffManager implements Manager<DayOff>
{

  DAOManager manager = new DAODaysOffManager();

  @Override
  public void insert(DayOff dayOff)
  {
    manager.insert(dayOff);
  }

  @Override
  public List<DayOff> getListOfEntities() {
    return manager.get();
  }

  @Override
  public void remove(DayOff dayOff)
  {
    manager.remove(dayOff);
  }

  @Override
  public void update(DayOff dayOff) {

  }
}
