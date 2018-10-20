package Entity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class test
{

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Workers");
    EntityManager em = emf.createEntityManager();
    EntityTransaction et = em.getTransaction();
    Worker worker = new Worker("Anton", "Pushkar", 1);
    et.begin();
    em.persist(worker);
    et.commit();
    em.close();
    emf.close();
  }
}
