package elevatorSimObjects;

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
  private int maxTripsBeforeMaintenance = 100;
  
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
   * Returns true if the move was successful.  False nothing changed as a result of the call
   */
  public boolean moveToFloor(Integer floorNum)
  {
    floorsPassedThisTrip = 0;
    
    // Some Safety Checks
    if (floorNum < 1 || floorNum > maxFloorNum)
    {
      // Create this exception
      throw FloorOutOfBoundsException("Floor Number is out of range!");
    }
    else if (floorNum == currentFloorNumber)
    {
      // Probly shouldn't happen, but we'll account for it anyway
      return false;
    }
    
    if (totalTrips > maxTripsBeforeMaintenance)
    {
      // We should have reported at the end of last trip,
      // So in theory we shouldn't get this request.  But check anyway
      reportNeedMaintenance();
      return false;
    }
    
    int floorDiff = floorNum - currentFloorNumber;
    if (floorDiff > 0)
    {
      // Going Uuuuppppp!
      while (currentFloorNumber < floorNum)
      {
        currentFloorNumber++;
        reportFloorChange();
        floorsPassedThisTrip++;
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
        floorsPassedThisTrip++;
        if (floorsToStopAt.contains(currentFloorNumber))
        {
          // We need to make a stop here
          stop();
          floorsToStopAt.remove(currentFloorNumber);
        }
      }
    }
    
    totalTrips++;
    if (totalTrips > maxTripsBeforeMaintenance)
    {
      reportNeedMaintenance();
    }
    
    return true;
  }
  
  public void reportFloorChange()
  {
    controller.elevatorReport(this, currentFloorNumber);
  }
  
  public void reportNeedMaintenance()
  {
    controller.requestMaintenance(this);
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
    // Open sesame!
  }
  
  public boolean closeDoors()
  {
    // Do stuff.  Once the doors are closed, return true.
    // If there is a problem, return false (we don't want to start again with the doors open!)
    return true;
  }
}
