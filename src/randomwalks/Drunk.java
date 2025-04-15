package randomwalks;

import simstation.*;
import mvc.*;

class Drunk extends MobileAgent
{
    public Drunk() {
        super("Drunk");
    }

    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(20) + 1;
        move(steps);
    }

}
