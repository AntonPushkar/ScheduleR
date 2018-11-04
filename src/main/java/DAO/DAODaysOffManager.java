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
    TypedQuery<DayOff> query = em.createQuery("select new DayOff(c.date) from DayOff c",
        DayOff.class);
    List<DayOff> listOfDayOff =query.getResultList();
    return listOfDayOff;
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
  public void update(DayOff dayOff) {

  }
}
