package flocking;

import mvc.*;
import simstation.*;

public class FlockingFactory extends WorldFactory
{
    public Model makeModel() { return new FlockingSimulation(); }
    public String getTitle() { return "Flocking";}
}
