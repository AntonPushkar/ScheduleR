package DAO;

import java.util.List;

public interface DAOManager<T>
{
  void insert(T t);
  List<T> get();
  void remove(T t);
  void update(T t);

}
