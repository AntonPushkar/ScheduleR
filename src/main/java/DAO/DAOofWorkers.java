package DAO;

import Entity.Worker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class DAOofWorkers
{
  private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Schedule");
  private static EntityManager em = emf.createEntityManager();

  public static EntityManager getEm() {
    return em;
  }
}
