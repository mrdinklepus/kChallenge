package src.elevatorSimObjects;

import elevatorController.BuildingController;

public class Elevator
{
  private BuildingController controller;
  private int totalTrips = 0;
  private int currentFloorNumber = 1;  // Assume elevators all start at floor 1
  private int floorsPassedThisTrip = 0;
  
  public Elevator(BuildingController controller)
  {
    this.controller = controller;
  }
  
  public void moveToFloor(int floorNum)
  {
    
  }
  
  public void reportFloorChange()
  {
    controller.elevatorReport(this, currentFloorNumber);
  }
  
  
}
