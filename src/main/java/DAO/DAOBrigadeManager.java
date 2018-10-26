package DAO;

import Entity.Brigade;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOBrigadeManager extends aDAOManager implements DAOManager<Brigade>
{
  private EntityManager em = aDAOManager.getEm();


  @Override
  public void insert(Brigade brigade)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(brigade);
    tm.commit();
  }

  public List<Brigade> get() {
    TypedQuery<Brigade> query = em.createQuery(
        "SELECT c from Brigade c",
        Entity.Brigade.class);
    List<Brigade> listOfBrigade = query.getResultList();
    return listOfBrigade;
  }


  @Override
  public void remove(Brigade brigade)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from Brigade c where c.brigadier =:Worker "
        + "and c.listOfWorkersInBrigade =:listWorkers"
        + " and c.numOfBrigade=:numOfBrigade and c.numOfBrigade =: numBrigade");
    query.setParameter("Worker", brigade.getBrigadier());
    query.setParameter("listWorkers", brigade.getListOfWorkersInBrigade());
    query.setParameter("numOfBrigade", brigade.getNumOfBrigade());
    query.executeUpdate();
    em.getTransaction().commit();

  }

  @Override
  public void update(Brigade brigade)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("UPDATE Brigade c set c.brigadier=:brigadier where c.numOfBrigade=:numOfBrigade");
    query.setParameter("brigadier", brigade.getBrigadier());
   // query.setParameter("listWorkers", brigade.getListOfWorkersInBrigade());
    query.setParameter("numOfBrigade", brigade.getNumOfBrigade());
    query.executeUpdate();
    em.getTransaction().commit();

  }

  public static void main(String[] args) {
    DAOBrigadeManager brigadeManager = new DAOBrigadeManager();
    List<Brigade> list = brigadeManager.get();
  }
}
