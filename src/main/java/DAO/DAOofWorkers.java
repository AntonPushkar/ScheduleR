package DAO;

import Entity.Workers;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOofWorkers
{
  EntityManagerFactory emf = Persistence.createEntityManagerFactory("Workers");
  EntityManager em = emf.createEntityManager();
  public void insert(Workers worker)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(worker);
    tm.commit();
  }

  public List<?> getDataFromDB()
  {
    TypedQuery<Workers> query = em.createQuery("SELECT new Entity.Workers(c.name, c.secName, c.numOfBrigade) from Workers c", Workers.class);
    List<Workers> listOfWorkers = query.getResultList();
    return listOfWorkers;
  }

  public static void main(String[] args) {
    new DAOofWorkers().getDataFromDB();
  }

  public void remove(Workers worker)
  {
    String name = worker.getName();
    String secName = worker.getSecName();
    int numOfBrigade = worker.getNumOfBrigade();
    int hashCode = worker.getHashcode();
    em.getTransaction().begin();
    Query query = em.createQuery("delete from Workers c where c.name=:name and c.secName =:secName"
        + " and c.numOfBrigade=:numOfBrigade and c.hashcode=:hashCode");
    query.setParameter("name", name);
    query.setParameter("secName", secName);
    query.setParameter("numOfBrigade", numOfBrigade);
    query.setParameter("hashCode", hashCode);
    query.executeUpdate();
    em.getTransaction().commit();
  }
}
