package mvc.simstation.randomwalks;

import mvc.*;
import mvc.simstation.*;

public class RandomWalkFactory extends WorldFactory
{
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}
