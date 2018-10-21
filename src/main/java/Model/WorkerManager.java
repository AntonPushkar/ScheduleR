package Model;

import DAO.DAOofWorkers;
import Entity.Workers;
import Util.ParstingNameOfWorker;
import java.util.List;

public class WorkerManager
{
  private static DAOofWorkers dao = new DAOofWorkers();
  public static void GenerateWorker(String fullName, int num )
  {
    String[] splitFullName = ParstingNameOfWorker.parsingNameOfWorker(fullName);
    String name;
    String secName;
    if(splitFullName.length==2) {
      name = splitFullName[0];
      secName = splitFullName[1];
      dao.insert(new Workers(name, secName, num));
    }
  }

  public static List<Workers> getWorker()
  {
    List<Workers> listOfWorkers = (List<Workers>) dao.getDataFromDB();
    return listOfWorkers;
  }

  public static void remove(Workers worker)
  {
    dao.remove(worker);
  }

}
