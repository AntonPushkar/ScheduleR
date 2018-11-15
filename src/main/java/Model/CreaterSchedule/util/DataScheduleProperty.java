package Model.CreaterSchedule.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class DataScheduleProperty
{

  private static FileInputStream inProp;
  private static final Properties scheduleProperties = new Properties();

  public static void writeProperty(String key, String value)
  {
    try {
      inProp = new FileInputStream("Schedule.properties");
      scheduleProperties.load(inProp);
      inProp.close();
      FileOutputStream outProp = new FileOutputStream("Schedule.properties");
      scheduleProperties.setProperty(key, value);
      String comment = "properties of Schedule";
      scheduleProperties.store(outProp, comment);
      outProp.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String readProperty(String key)
  {
    String value = "null";
    try {
      inProp = new FileInputStream("Schedule.properties");
      scheduleProperties.load(inProp);
      value=scheduleProperties.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return value;
  }
}
