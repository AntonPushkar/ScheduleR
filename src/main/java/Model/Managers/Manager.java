package Model.Managers;

import java.util.List;

public interface Manager<T>
{
  void insert(T t);
  List<T> getListOfEntities();
  void remove(T t);
  void update(T t);
}
