package mvc.simstation.flocking;

import mvc.*;
import mvc.simstation.*;
import mvc.simstation.randomwalks.RandomWalkSimulation;

public class FlockingFactory extends WorldFactory
{
    public Model makeModel() { return new FlockSimulation(); }
    public String getTitle() { return "Flocking";}
}
