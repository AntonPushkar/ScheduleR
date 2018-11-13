package Model.Managers;

import DAO.DAODaysOffManager;
import DAO.DAOManager;
import Entity.DayOff;
import java.util.List;

public class DayOffEntityManager implements EntityManager<DayOff>
{

  DAOManager<DayOff> manager = new DAODaysOffManager();

  @Override
  public void insert(DayOff dayOff)
  {
    manager.insert(dayOff);
  }

  @Override
  public List<DayOff> getListEntities() {
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
