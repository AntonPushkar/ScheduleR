package Model;

public enum FieldsOfProperties
{
  FIRST_BRIGADE_LAST_WEEK ("firstBrigadeOfLastWeek"),
  MONTH_OF_LAST_SCHEDULE ("MonthOfLastSchedule"),
  LAST_BRIGADE_IN_MONTH ("lastBrigadeInMonth"),
  LAST_BRIGADE_IN_WEEK ("lastBrigadeInWeek");

  private final String text;
  FieldsOfProperties(final String text)
  {
    this.text = text;
  }

  @Override
  public String toString() {
    return text;
  }
}
