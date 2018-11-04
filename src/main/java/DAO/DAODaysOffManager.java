package DAO;

import Entity.DaysOff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAODaysOffManager implements DAOManager<DaysOff>  {

  private EntityManager em = aDAOManager.getEm();

  @Override
  public void insert(DaysOff daysOff)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(daysOff);
    tm.commit();
  }

  @Override
  public List<DaysOff> get()
  {
    TypedQuery<DaysOff> query = em.createQuery("select new DaysOff(c.date) from DaysOff c",
        DaysOff.class);
    List<DaysOff> listOfDaysOff=query.getResultList();
    return listOfDaysOff;
  }

  @Override
  public void remove(DaysOff daysOff)
  {
    em.getTransaction().begin();
    Query query = em.createQuery("delete from DaysOff c where c.date=:date");
    query.setParameter("date", daysOff.getDate());
    query.executeUpdate();
    em.getTransaction().commit();
  }

  @Override
  public void update(DaysOff daysOff) {

  }
}
