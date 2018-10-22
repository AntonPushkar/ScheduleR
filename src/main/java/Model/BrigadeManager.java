package Model;

import DAO.DAOofWorkers;
import Entity.Brigade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BrigadeManager implements Manager<Brigade>
{

  EntityManager em = DAOofWorkers.getEm();

  @Override
  public void insert(Brigade brigade)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(brigade);
    tm.commit();

  }

  @Override
  public List<Brigade> getDataFromDB() {
    return null;
  }

  @Override
  public void remove(Brigade brigade) {

  }

  public static void main(String[] args) {
    BrigadeManager dm = new BrigadeManager();
    dm.insert(new Brigade(1));
  }
}
