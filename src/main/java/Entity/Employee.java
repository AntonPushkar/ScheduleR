package Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Employee
{
  @Id @GeneratedValue(strategy = GenerationType.TABLE)
  private int id;
  private String name;
  private String secName;
  private String personnelNum;

  public Employee()
  {

  }

  public Employee(String name, String secName, String personnelNum)
  {
    this.personnelNum = personnelNum;
    this.name = name;
    this.secName = secName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSecName() {
    return secName;
  }

  public void setSecName(String secName) {
    this.secName = secName;
  }

  public String getPersonnelNum() {
    return personnelNum;
  }
}
