package Model;

import DAO.DAOManager;
import DAO.DAOWorkerManager;
import Entity.Brigade;
import Entity.Worker;
import Util.ParstingNameOfWorker;
import java.util.List;

public class WorkerManager implements Manager<Worker> {

  private List<Worker> listWorker;
  private DAOManager workerManager = new DAOWorkerManager();
  private BrigadeManager brigadeManager = new BrigadeManager();
  private List<Brigade> listOfBrigade = brigadeManager.getListOfEntities();

  public void prepareWorker(String fullName, int numBrigade, String pesonnelNum) {
    String[] splitFullName = ParstingNameOfWorker.parsingNameOfWorker(fullName);
    String name;
    String secName;
    if (splitFullName.length == 2) {
      name = splitFullName[0];
      secName = splitFullName[1];
      Worker worker = new Worker(name, secName, numBrigade, pesonnelNum);
      listOfBrigade.get(worker.getNumOfBrigade()-1).addWorker(worker);
      worker.setBrigade(listOfBrigade.get(worker.getNumOfBrigade()-1));
      insert(worker);
      //brigadeManager.update(listOfBrigade.get(worker.getNumOfBrigade()-1));
    }
  }

  @Override
  public void insert(Worker worker) {
    workerManager.insert(worker);
  }

  @Override
  public List<Worker> getListOfEntities() {
    return listWorker=workerManager.get();
  }

  @Override
  public void remove(Worker worker)
  {
    int numBrigade = worker.getNumOfBrigade()-1;
    listOfBrigade.get(numBrigade).getListOfWorkersInBrigade().remove(worker);
    workerManager.remove(worker);
    brigadeManager.update(listOfBrigade.get(numBrigade));
  }

  @Override
  public void update(Worker worker)
  {


  }
}
