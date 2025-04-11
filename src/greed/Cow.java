package greed;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

public class Cow extends MobileAgent {

    int energy;
    int greediness;
    Patch location;
    int moveEnergy;

    public Cow(String name, int energy, int greediness, int moveEnergy){
        super(name);
        this.energy = energy;
        this.greediness = greediness;
        this.moveEnergy = moveEnergy;
    }

    public void update() {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }


}
