package Model;

import DAO.DAOofWorkers;
import Entity.Worker;
import Util.ParstingNameOfWorker;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class WorkerManager implements Manager<Worker>
{
  private static EntityManager em = DAOofWorkers.getEm();

  public void insert(Worker worker)
  {
      EntityTransaction tm = em.getTransaction();
      tm.begin();
      em.persist(worker);
      tm.commit();
  }

  public void GenerateWorker(String fullName, int num )
  {
    String[] splitFullName = ParstingNameOfWorker.parsingNameOfWorker(fullName);
    String name;
    String secName;
    //************For TEST
    int pesonnelNum = (int) (Math.random()*10);
    //*************FOR TEST ^^^^^^^^^^^^^^^^^^
    if(splitFullName.length==2) {
      name = splitFullName[0];
      secName = splitFullName[1];
      insert(new Worker(name, secName, num, pesonnelNum ));
    }
  }


  public List<Worker> getDataFromDB()
  {
    TypedQuery<Worker> query = em.createQuery("SELECT new Entity.Worker(c.name, c.secName, c.numOfBrigade, c.PersonnelNum) from Worker c", Worker.class);
    List<Worker> listOfWorkers = query.getResultList();
    return listOfWorkers;
  }

  public void remove(Worker worker)
  {
    String name = worker.getName();
    String secName = worker.getSecName();
    int numOfBrigade = worker.getNumOfBrigade();
    int personnelNum = worker.getPersonnelNum();
    em.getTransaction().begin();
    Query query = em.createQuery("delete from Worker c where c.name=:name and c.secName =:secName"
        + " and c.numOfBrigade=:numOfBrigade and c.PersonnelNum=:personnelNum");
    query.setParameter("name", name);
    query.setParameter("secName", secName);
    query.setParameter("numOfBrigade", numOfBrigade);
    query.setParameter("personnelNum", personnelNum);
    query.executeUpdate();
    em.getTransaction().commit();
  }




}
