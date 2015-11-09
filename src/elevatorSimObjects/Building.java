package src.elevatorSimObjects;

public class Building
{
  private int numFloors;
  private List<Elevator> elevators;
  
  public Building(int numFloors, List<Elevator> elevators)
  {
    this.numFloors = numFloors;
    this.elevators = elevators;
  }
}
