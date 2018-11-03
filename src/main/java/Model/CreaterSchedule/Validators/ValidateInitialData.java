package Controller.Validators;

import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.Managers.BrigadeManager;

public class ValidateInitialData
{
  public static boolean validateInitialBrigade()
  {
    boolean result = false;
    String numOfBrigade = DataScheduleProperty.readProperty("firstBrigadeOfLastWeek");
    if(numOfBrigade == null)
    {
      return false;
    }
    else
    {
      try {
        int numBrigade = Integer.parseInt(numOfBrigade);
        if(numBrigade == -1)
        {
          return false;
        }
        else if(numBrigade > -1 && numBrigade<= (new BrigadeManager().getListOfEntities().size()))
        {
          result = true;
        }

      }
      catch (NumberFormatException e)
      {
        return false;
      }
    }
    return result;
  }
}
