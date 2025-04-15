package flocking;

import mvc.*;
import simstation.*;

public class Bird extends MobileAgent
{
    private int speed;

    public Bird()
    {
        super("Bird");
    }

    public int getSpeed()
    {
        return speed;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }

    public void setHeading(Heading heading)
    {
        this.heading = heading;
    }

    public void update()
    {
        // Move first:
        heading = Heading.random();
        int speed = Utilities.rng.nextInt(20) + 1;
        move(speed);

        Agent nearbyBird = world.getNeighbor(this, 10);

        if (nearbyBird instanceof Bird)
        {
            Bird neighboringBird = (Bird)nearbyBird;

            // Set current bird's speed & heading to be that of neighbor's:
            this.setSpeed(neighboringBird.getSpeed());
            this.setHeading(neighboringBird.getHeading());
        }
    }
}
