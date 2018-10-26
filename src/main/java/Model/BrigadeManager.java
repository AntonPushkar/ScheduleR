package Model;

import DAO.DAOBrigadeManager;
import DAO.DAOManager;
import Entity.Brigade;
import java.util.List;

public class BrigadeManager implements Manager<Brigade>
{
  private List<Brigade> listOfBrigade;
  private DAOManager brigadeManager = new DAOBrigadeManager();


  @Override
  public void insert(Brigade brigade)
  {
    brigadeManager.insert(brigade);
  }

  @Override
  public List<Brigade> getListOfEntities()
  {
    return listOfBrigade=brigadeManager.get();
  }

  @Override
  public void remove(Brigade brigade)
  {
    brigadeManager.remove(brigade);
  }

  @Override
  public void update(Brigade brigade) {
    brigadeManager.update(brigade);
  }
}
