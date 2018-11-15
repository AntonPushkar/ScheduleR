package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public interface DAOManager<T>
{

  void insert(T t);
  List<T> get();
  void remove(T t);
  void update(T t);

  EntityManagerFactory emf = Persistence.createEntityManagerFactory("Schedule");
  EntityManager em = emf.createEntityManager();

  static EntityManager em()
  {
    return em;
  }


}
