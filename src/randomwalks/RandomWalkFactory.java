package randomwalks;

import mvc.*;
import simstation.*;

public class RandomWalkFactory extends WorldFactory {
    public Model makeModel() { return new RandomWalkSimulation(); }
    public String getTitle() { return "Random Walks";}
}
