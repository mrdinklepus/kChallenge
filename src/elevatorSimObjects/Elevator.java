package src.elevatorSimObjects;

import java.util.HashSet;
import java.util.Set;

import elevatorController.BuildingController;

public class Elevator
{
  private BuildingController controller;
  private int totalTrips = 0;
  private Integer currentFloorNumber = 1;  // Assume elevators all start at floor 1
  private int floorsPassedThisTrip = 0;
  private int maxFloorNum;
  
  // addedStop
  private Set<Integer> floorsToStopAt = new HashSet<Integer>();
  
  public Elevator(BuildingController controller, int maxFloorNum)
  {
    this.controller = controller;
    this.maxFloorNum = maxFloorNum;
  }
  
  /**
   * This constitutes starting a 'trip'
   * This should be done in its own thread independent of other elevators
   */
  public void moveToFloor(Integer floorNum)
  {
    if (floorNum < 1 || floorNum > maxFloorNum)
    {
      // Create this exception
      throw FloorOutOfBoundsException("Floor Number is out of range!");
    }
    else if (floorNum == currentFloorNumber)
    {
      // Probly shouldn't happen, but we'll account for it anyway
      return;
    }
    
    int floorDiff = floorNum - currentFloorNumber;
    if (floorDiff > 0)
    {
      // Going Uuuuppppp!
      while (currentFloorNumber < floorNum)
      {
        currentFloorNumber++;
        reportFloorChange();
        if (floorsToStopAt.contains(currentFloorNumber))
        {
          // We need to make a stop here
          stop();
          floorsToStopAt.remove(currentFloorNumber);
        }
      }
    }
    else
    {
      // Going Dddooowwwwwwnnnnn!
      while (currentFloorNumber > floorNum)
      {
        currentFloorNumber--;
        reportFloorChange();
        if (floorsToStopAt.contains(currentFloorNumber))
        {
          // We need to make a stop here
          stop();
          floorsToStopAt.remove(currentFloorNumber);
        }
      }
    }
  }
  
  public void reportFloorChange()
  {
    controller.elevatorReport(this, currentFloorNumber);
  }
  
  public void reportNeedMaintenance()
  {
    
  }
  
  public void addStopAtFloor(Integer floorNum)
  {
    floorsToStopAt.add(floorNum);
  }
  
  public void stop()
  {
    openDoors();
    Thread.sleep(1000 * 10); // wait 10 seconds?
    if (!closeDoors())
    {
      throw Exception();
    }
  }
  
  public void openDoors()
  {
    
  }
  
  public boolean closeDoors()
  {
    // Do stuff.  Once the doors are closed, return true.
    // If there is a problem, return false (we don't want to start again with the doors open!)
    return true;
  }
}
