package Entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Brigade
{
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "numOfBrigade")
  private int numoFBrigade;
  @Column(name = "brigadier")
  private Worker brigadier;
  @ElementCollection(fetch = FetchType.EAGER)
  private List<Worker> listOfWorkersInBrigade;



}
