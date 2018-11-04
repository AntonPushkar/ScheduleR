package Model.Managers;

import DAO.DAOManager;
import DAO.DAOWorkerManager;
import Entity.Brigade;
import Entity.Worker;
import java.util.List;

public class WorkerManager implements Manager<Worker> {

  private DAOManager<Worker> workerManager = new DAOWorkerManager();
  private BrigadeManager brigadeManager = new BrigadeManager();
  private List<Brigade> listOfBrigade = brigadeManager.getListOfEntities();

  @Override
  public void insert(Worker worker) {
    workerManager.insert(worker);
  }

  @Override
  public List<Worker> getListOfEntities() {
    List<Worker> listWorker;
    return listWorker =workerManager.get();
  }

  @Override
  public void remove(Worker worker)
  {
    int numBrigade = worker.getNumOfBrigade()-1;
    //listOfBrigade.get(numBrigade).getListOfWorkersInBrigade().remove(worker);
    workerManager.remove(worker);
   // brigadeManager.update(listOfBrigade.get(numBrigade));
  }

  @Override
  public void update(Worker worker)
  {
    workerManager.update(worker);
  }
}
