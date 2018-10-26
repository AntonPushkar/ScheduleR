package Model;

import DAO.DAOManager;
import DAO.DAOWorkerManager;
import Entity.Worker;
import Util.ParstingNameOfWorker;
import java.util.List;

public class WorkerManager implements Manager<Worker>
{
  private List<Worker> listWorker;
  private DAOManager workerManager = new DAOWorkerManager();

  public void prepareWorker(String fullName, int num)
  {
    String[] splitFullName = ParstingNameOfWorker.parsingNameOfWorker(fullName);
    String name;
    String secName;
    //************For TEST
    String pesonnelNum = String.valueOf(Math.random()*10);
    //*************FOR TEST ^^^^^^^^^^^^^^^^^^
    if(splitFullName.length==2) {
      name = splitFullName[0];
      secName = splitFullName[1];
      insert(new Worker(name, secName, num, pesonnelNum ));
    }
  }

  @Override
  public void insert(Worker worker)
  {
    workerManager.insert(worker);
  }

  @Override
  public List<Worker> getListOfEntities()
  {
    if(listWorker == null)
      listWorker = workerManager.get();
    return listWorker;
  }

  @Override
  public void remove(Worker worker)
  {
    workerManager.remove(worker);
  }



}
