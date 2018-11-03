package Model.Managers;

import DAO.DAOBrigadeManager;
import DAO.DAOManager;
import Entity.Brigade;
import java.util.ArrayList;
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
    listOfBrigade=brigadeManager.get();
    if(listOfBrigade.isEmpty()) {
      fillBrigade();
      listOfBrigade=brigadeManager.get();
    }
    return listOfBrigade;
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

  public void fillBrigade()
  {
    if(listOfBrigade!= null && listOfBrigade.isEmpty())
    {
      for(int i=1; i<=3; i++) {
        insert(new Brigade(i));
      }
    }
  }

}
