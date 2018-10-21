import Entity.Worker;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionManager;


public class DAO
{
  @PersistenceContext
  EntityManager em;

  public void insert(Worker worker)
  {
    EntityTransaction tm = em.getTransaction();
    tm.begin();
    em.persist(worker);
    tm.commit();

  }
}
