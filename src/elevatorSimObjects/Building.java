package elevatorSimObjects;

import java.util.HashSet;
import java.util.Set;

import elevatorController.BuildingController;

public class Building
{
  private BuildingController controller;
  private Set<Elevator> elevators;
  private int numberOfFloors;
  
  // Other stuff maybe not applicable to this
  private int numberOfRooms;
  private int numberOfFireExtinguishers;
  
  public Building(int numElevators, int numFloors)
  {
    controller = new BuildingController(numFloors);
    numberOfFloors = numFloors;
    
    for (int i = 0; i < numElevators; i++)
    {
      Elevator e = new Elevator(controller, numFloors);
      elevators.add(e);
      controller.addElevator(e);
    }
  }
}
