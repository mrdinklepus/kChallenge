package elevatorController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import elevatorSimObjects.Elevator;

public class BuildingController
{
  private int numFloors;
  
  // Map the elevators to their current floors
  private Map<Elevator, Integer> elevators = new HashMap<>();
  
  // Elevators NOT in maintenance
  private Set<Elevator> availableElevators = new HashSet<>();
  
  
  public BuildingController()
  {
    
  }
  
  /**
   * I suppose for here, we may want to kick these reports handling into different threads
   * for scalability
   */
  public void elevatorReport(Elevator elevator, int newFloor)
  {
    elevators.put(elevator, newFloor);
  }
  
  public void requestElevatorToCome(int floorNum)
  {
    
  }
  
  public void elevatorButtonPushed(Elevator elevator, int requestedFloorNum)
  {
    elevator.addStopAtFloor(requestedFloorNum);
  }
  
  public void findClosestAvailableElevator(int forFloorNum)
  {
    
  }

  public void requestMaintenance(Elevator elevator)
  {
    availableElevators.remove(elevator);
  }
}
