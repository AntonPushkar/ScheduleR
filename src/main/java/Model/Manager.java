package Model;

import java.util.List;

public interface Manager<T>
{
  void insert(T t);
  List<T> getDataFromDB();
  void remove(T t);

}
