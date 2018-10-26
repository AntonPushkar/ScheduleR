package DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class aDAOManager
{
  private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Schedule");
  private static EntityManager em = emf.createEntityManager();

  public static EntityManager getEm() {
    return em;
  }
}
