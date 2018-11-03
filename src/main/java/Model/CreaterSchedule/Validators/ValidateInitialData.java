package Model.CreaterSchedule.Validators;

import Model.CreaterSchedule.util.DataScheduleProperty;
import Model.Managers.BrigadeManager;

public class ValidateInitialData
{
  public static boolean validateFirstBrigadeOfLastWeek()
  {
    boolean result = false;
    String key = "firstBrigadeOfLastWeek";
    String value = DataScheduleProperty.readProperty(key);
    result = validateNumOfBrigade(value);
    return result;
  }

  public static boolean validateLastBrigadeInMonth()
  {
    boolean result = false;
    String key = "lastBrigadeInMonth";
    String value = DataScheduleProperty.readProperty(key);
    result = validateNumOfBrigade(value);
    return result;
  }

  private static boolean validateNumOfBrigade(String value)
  {
    boolean result = false;
    if(value == null)
    {
      return false;
    }
    else
    {
      try {
        int numBrigade = Integer.parseInt(value);
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
