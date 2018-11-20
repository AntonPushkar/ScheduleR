package Model.Managers;

import DAO.DAOManager;
import DAO.DAOWorkerManager;
import Entity.Brigade;
import Entity.Worker;
import java.util.List;

public class WorkerEntityManager implements EntityManager<Worker> {

  private DAOManager<Worker> workerManager = new DAOWorkerManager();
  private BrigadeEntityManager brigadeManager = new BrigadeEntityManager();
  private List<Brigade> listOfBrigade = brigadeManager.getListEntities();

  @Override
  public void insert(Worker worker) {
    int numBrigade = worker.getNumOfBrigade()-1;
    Brigade brigade = listOfBrigade.get(numBrigade);
    brigade.addWorker(worker);
    workerManager.insert(worker);
  }

  @Override
  public List<Worker> getListEntities() {
    return workerManager.get();
  }

  @Override
  public void remove(Worker worker)
  {
    int numBrigade = worker.getNumOfBrigade()-1;
    Brigade brigade = listOfBrigade.get(numBrigade);
    listOfBrigade.get(numBrigade).getListOfWorkersInBrigade().remove(worker);
    workerManager.remove(worker);
  }



  @Override
  public void update(Worker worker)
  {
    workerManager.update(worker);
  }
}
