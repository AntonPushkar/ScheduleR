package DAO;

import Entity.Day;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAODayManager implements DAOManager<Day> {

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
    List<Day> listOfDays = query.getResultList();
    return listOfDays;
  }

  @Override
  public void remove(Day day)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from Day c where c.date=:date");
    query.setParameter("date", day.getDate());
    query.executeUpdate();
    em.getTransaction().commit();
  }

  @Override
  public void update(Day day)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("UPDATE Day c set c.dayOff=:dayOff, c.brigadeNigth=:brigadeNight"
        + ", c.brigadeDay=:brigadeDay");
    query.setParameter("dayOff", day.isDayOff());
    query.setParameter("brigadeNight", day.getBrigadeNight());
    query.setParameter("brigadeDay", day.getBrigadeDay());
    query.executeUpdate();
    em.getTransaction().commit();
  }
}
