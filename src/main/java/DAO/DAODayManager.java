package DAO;

import Entity.Day;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAODayManager implements DAOManager<Day> {

  private final EntityManager em = DAOManager.em();
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
    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Day> criteriaQuery = builder.createQuery(Day.class);
    Root<Day> c = criteriaQuery.from(Day.class);
    criteriaQuery.select(c);
    Query query = em.createQuery(criteriaQuery);
    return (List<Day>) query.getResultList();
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
    Query query = em.createQuery("UPDATE Day c set c.dayOff=:dayOff, c.brigadeNight=:brigadeNight"
        + ", c.brigadeDay=:brigadeDay where c.date=:date");
    query.setParameter("dayOff", day.isDayOff());
    query.setParameter("brigadeNight", day.getBrigadeNight());
    query.setParameter("brigadeDay", day.getBrigadeDay());
    query.setParameter("date", day.getDate());
    query.executeUpdate();
    em.getTransaction().commit();
  }
}
