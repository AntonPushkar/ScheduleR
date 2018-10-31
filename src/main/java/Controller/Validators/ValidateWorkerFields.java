package Controller.Validators;

import java.text.NumberFormat;
import javax.persistence.criteria.CriteriaBuilder.In;

public class ValidateWorkerFields
{

  public static boolean validateWorker(String name, String secName, String numOfBrigade, String persNum)
  {
    int numBrigade=0;
    if(!numOfBrigade.isEmpty() && numOfBrigade!=null)
    {
      try {
        numBrigade = Integer.parseInt(numOfBrigade);
      }
      catch (NumberFormatException e)
      {
        numBrigade = 0;
      }
    }
    boolean isValid = validateName(name) & validateSecName(secName) & validateNumBrigade(numBrigade) &
        validatePersNum(persNum);
    return isValid;
  }

  private static boolean validateName(String name)
  {
    if(name != null && (!name.isEmpty()))
      return true;
    return false;
  }

  private static boolean validateSecName(String secName)
  {
    if(secName != null && (!secName.isEmpty()))
      return true;
    return false;
  }

  private static boolean validateNumBrigade(int NumOfBrigade)
  {
    if(NumOfBrigade > 0 && NumOfBrigade <= 3)
      return true;
    return false;
  }

  private static boolean validatePersNum(String persNum)
  {
    if(persNum != null && (!persNum.isEmpty()))
      return true;
    return false;
  }



}
