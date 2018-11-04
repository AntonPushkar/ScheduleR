package DAO;

import Entity.Day;
import Entity.Worker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAODayManager extends aDAOManager implements DAOManager<Day> {

  private EntityManager em = aDAOManager.getEm();
  @Override
  public void insert(Day day)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(day);
    tm.commit();
  }

  @Override
  public List<Day> get() {
    TypedQuery<Day> query = em.createQuery("SELECT new"
        + " Entity.Day(c.brigadeDay, c.brigadeNigth, c.date, c.dayOff) from Day c", Day.class);
    List<Day> listOfWorkers = query.getResultList();
    return listOfWorkers;
  }

  @Override
  public void remove(Day day)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from Day c where c.date=:date");
    query.setParameter("date", day.getDate());
  }

  @Override
  public void update(Day day)
  {

  }
}
