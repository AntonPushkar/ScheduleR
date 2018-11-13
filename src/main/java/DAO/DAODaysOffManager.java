package DAO;

import Entity.DayOff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAODaysOffManager implements DAOManager<DayOff>  {

  private EntityManager em = aDAOManager.getEm();

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
            + "(c.dateOfDayOff, c.isDayOff, c.cancelSeocndShift, c.cancelFirstShift) from DayOff c",
        DayOff.class);
    return query.getResultList();
  }

  @Override
  public void remove(DayOff dayOff)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from DayOff c where c.dateOfDayOff=:date");
    query.setParameter("date", dayOff.getDateOfDayOff());
    query.executeUpdate();
    em.getTransaction().commit();
  }

  @Override
  public void update(DayOff dayOff)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("UPDATE DayOff c set c.cancelFirstShift=:isCancelFirstShift,"
        + "c.cancelSeocndShift=:isCancelSecondShift, c.isDayOff=:isDayOff where c.dateOfDayOff =:date");
    query.setParameter("isCancelFirstShift", dayOff.isCancelFirstShift());
    query.setParameter("isCancelSecondShift", dayOff.isCancelSeocndShift());
    query.setParameter("isDayOff", dayOff.isDayOff());
    query.setParameter("date", dayOff.getDateOfDayOff());
    query.executeUpdate();
    em.getTransaction().commit();

  }
}
