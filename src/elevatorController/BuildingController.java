package elevatorController;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import elevatorSimObjects.Elevator;

public class BuildingController
{
  // Might not need this stored, but keeping it just in case we do
  private int numFloors;
  
  // Map the elevators to their current floors
  private Map<Elevator, Integer> elevators = new HashMap<>();
  
  // Elevators NOT in maintenance
  private Set<Elevator> availableElevators = new HashSet<>();
  
  /**
   * Initialization.  In the future, Probably make this more configurable
   */
  public BuildingController(int numFloors, int numElevators)
  {
    this.numFloors = numFloors;
    for (int i = 0; i < numElevators; i++)
    {
      Elevator e = new Elevator(this, numFloors);
      elevators.put(e, 1);
      availableElevators.add(e);
    }
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
    Elevator elevator = findClosestAvailableElevator(floorNum);
    
    if (elevator.isMoving())
    {
      elevator.addStopAtFloor(floorNum);
    }
    else
    {
      elevator.moveToFloor(floorNum);
    }
  }
  
  /**
   * this might be internal to elevator?  
   * For now, just assume the actual stimulus comes from the controller
   */
  public void elevatorButtonPushed(Elevator elevator, int requestedFloorNum)
  {
    elevator.addStopAtFloor(requestedFloorNum);
  }
  
  public Elevator findClosestAvailableElevator(int forFloorNum)
  {
    // For sake of time, we'll just explain what to do here
    // First, (not sure the most efficient here, but would figure it out)
    // perhaps loop through the map and check for closest elevator.  Check if its moving.
    // If not, start it on a new trip.  If it is, check if its moving toward the floor.
    // If so, add the stop.  If not, continue and check the next closest elevator & so on.
    // ALSO, verify that the elevator is available.
    
    // Ultimately, here we would have an algorithm looking for most probably the best case.
    // we sort of have to think through and trade off the time to search through the list
    // over and over looking fro the best case vs taking the first one (that can stop) we come to and assuming
    // it is the best option.
  }

  public void requestMaintenance(Elevator elevator)
  {
    availableElevators.remove(elevator);
  }
}
