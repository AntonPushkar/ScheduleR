package Controller.Validators;

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
    return validateName(name) & validateSecName(secName) & validateNumBrigade(numBrigade) &
        validatePersNum(persNum);
  }

  private static boolean validateName(String name)
  {
    return name != null && (!name.isEmpty());
  }

  private static boolean validateSecName(String secName)
  {
    return secName != null && (!secName.isEmpty());
  }

  private static boolean validateNumBrigade(int NumOfBrigade)
  {
    return NumOfBrigade > 0 && NumOfBrigade <= 3;
  }

  private static boolean validatePersNum(String persNum)
  {
    return persNum != null && (!persNum.isEmpty());
  }



}
