package Model.Managers;

import Model.Manager;

public interface EntityManager<T> extends Manager
{
  void insert(T t);
  void remove(T t);
  void update(T t);
}
