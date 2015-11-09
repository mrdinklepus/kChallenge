package src.elevatorSimObjects;

import elevatorController.BuildingController;

public class Elevator
{
  private BuildingController controller;
  private int totalTrips = 0;
  private int currentFloorNumber = 1;  // Assume elevators all start at floor 1
  private int floorsPassedThisTrip = 0;
  private int maxFloorNum;
  
  // addedStop
  private int floorToStopAt;
  
  public Elevator(BuildingController controller, int maxFloorNum)
  {
    this.controller = controller;
    this.maxFloorNum = maxFloorNum;
  }
  
  /**
   * This constitutes starting a 'trip'
   * This should be done in its own thread independent of other elevators
   */
  public void moveToFloor(int floorNum)
  {
    if (floorNum < 1 || floorNum > maxFloorNum)
    {
      // Create this exception
      throw FloorOutOfBoundsException("Floor Number is out of range!");
    }
    
    int floorDiff = floorNum - currentFloorNumber;
    for (int i = currentFloorNumber; i <= floorNum; i++)
    {
      if (floorToStopAt == 0)
      {
        currentFloorNumber++;
        reportFloorChange();
      }
    }
  }
  
  public void reportFloorChange()
  {
    controller.elevatorReport(this, currentFloorNumber);
  }
  
  public void addStopAtFloor(int floorNum)
  {
    
  }
}
