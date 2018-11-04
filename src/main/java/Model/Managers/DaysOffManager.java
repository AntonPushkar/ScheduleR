package Model.Managers;

import DAO.DAODaysOffManager;
import DAO.DAOManager;
import Entity.DaysOff;
import java.util.List;

public class DaysOffManager implements Manager<DaysOff>
{

  DAOManager manager = new DAODaysOffManager();

  @Override
  public void insert(DaysOff daysOff)
  {
    manager.insert(daysOff);
  }

  @Override
  public List<DaysOff> getListOfEntities() {
    return manager.get();
  }

  @Override
  public void remove(DaysOff daysOff)
  {
    manager.remove(daysOff);
  }

  @Override
  public void update(DaysOff daysOff) {

  }
}
