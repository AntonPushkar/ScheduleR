package DAO;

import Entity.DayOff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAODaysOffManager implements DAOManager<DayOff>  {

  private final EntityManager em = DAOManager.em();


  @Override
  public void insert(DayOff dayOff)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(dayOff);
    tm.commit();
  }

  @Override
  public List<DayOff> get()
  {
    TypedQuery<DayOff> query = em.createQuery("select new DayOff"
            + "(c.date, c.isDayOff,c.cancelFirstShift, c.cancelSecondShift) from DayOff c",
        DayOff.class);
    return query.getResultList();
  }

  @Override
  public void remove(DayOff dayOff)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from DayOff c where c.date=:date");
    query.setParameter("date", dayOff.getDate());
    query.executeUpdate();
    em.getTransaction().commit();
  }

  @Override
  public void update(DayOff dayOff)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("UPDATE DayOff c set c.cancelFirstShift=:isCancelFirstShift,"
        + "c.cancelSecondShift=:isCancelSecondShift, c.isDayOff=:isDayOff where c.date =:date");
    query.setParameter("isCancelFirstShift", dayOff.isCancelFirstShift());
    query.setParameter("isCancelSecondShift", dayOff.isCancelSecondShift());
    query.setParameter("isDayOff", dayOff.isDayOff());
    query.setParameter("date", dayOff.getDate());
    query.executeUpdate();
    em.getTransaction().commit();

  }
}
