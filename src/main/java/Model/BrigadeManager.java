package Model;

import DAO.DAOBrigadeManager;
import DAO.DAOManager;
import DAO.DAOWorkerManager;
import Entity.Brigade;
import java.util.List;

public class BrigadeManager implements Manager<Brigade>
{
  private List<Brigade> listOfBrigade;
  private DAOManager brigadeManager = new DAOBrigadeManager();


  @Override
  public void insert(Brigade brigade) {

  }

  @Override
  public List<Brigade> getListOfEntities()
  {
    if(listOfBrigade == null)
      listOfBrigade = brigadeManager.get();
    return listOfBrigade;
  }

  @Override
  public void remove(Brigade brigade) {

  }
}
