package Model.Managers;

import Entity.Day;
import java.util.List;

public class DayManager implements Manager<Day>
{

  public void insertListDays(List<Day> list)
  {
    for(int i=0; i<list.size(); i++)
      insert(list.get(i));
  }

  @Override
  public void insert(Day day)
  {

  }

  @Override
  public List<Day> getListOfEntities() {
    return null;
  }

  @Override
  public void remove(Day day) {

  }

  @Override
  public void update(Day day)
  {

  }
}
