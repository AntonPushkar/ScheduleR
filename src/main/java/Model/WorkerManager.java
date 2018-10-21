package Model;

import DAO.DAOofWorkers;
import Entity.Workers;
import Util.ParstingNameOfWorker;

public class GenerateWorker
{
  public static void GenerateWorker(String fullName, int num )
  {
    String[] splitFullName = ParstingNameOfWorker.parsingNameOfWorker(fullName);
    String name;
    String secName;
    if(splitFullName.length==2) {
      name = splitFullName[0];
      secName = splitFullName[1];
      Workers workers = new Workers(name, secName, num);
      DAOofWorkers dao = new DAOofWorkers();
      dao.insert(workers);
    }

  }

}
