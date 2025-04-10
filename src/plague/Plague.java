package plague;

import simstation.*;
import java.util.*;
import mvc.*;

public class Plague extends Agent
{
    private boolean isInfected;

    public Plague()
    {
        super("Plague Agent");
        isInfected = false;
    }

    public boolean isInfected()
    {
        return isInfected;
    }

    public void infect()
    {
        isInfected = true;
    }

    @Override
    public void update()
    {
        boolean previousStatus = isInfected;

        Agent neighbor = world.getNeighbor(this, 10);

        if (neighbor instanceof Plague)
        {
            Plague p = (Plague) neighbor;

            if (p.isInfected() && !this.isInfected())
            {
                int n = Utilities.rng.nextInt(100);
                if (n < PlagueRun.VIRULENCE)
                {
                    this.infect();
                }
            }
        }

        int dx = Utilities.rng.nextInt(11) - 5;
        int dy = Utilities.rng.nextInt(11) - 5;
        setX((getX() + dx + World.size) % World.size);
        setY((getY() + dy + World.size) % World.size);

        if (previousStatus != isInfected)
        {
            ((World) world).updateStatistics();
        }
    }
}