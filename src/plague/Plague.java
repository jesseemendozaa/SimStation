package plague;

import simstation.*;
import mvc.*;

import java.awt.*;

public class Plague extends Agent
{
    private boolean isInfected;
    private int timeInfected;
    private int fatalRecover;
    public boolean isDead;

    public Plague(int fatalRecover)
    {
        super("Plague Agent");
        isInfected = false;
        timeInfected = 0;
        this.fatalRecover = fatalRecover;
        isDead = false;
    }

    public boolean isInfected()
    {
        return isInfected;
    }

    public void infect()
    {
        isInfected = true;
        timeInfected = 0;
    }

    @Override
    public void update()
    {
        // boolean previousStatus = isInfected;

        Agent neighbor = world.getNeighbor(this, 10);

        if (neighbor instanceof Plague)
        {
            Plague p = (Plague) neighbor;

            if ((p.isInfected() && !this.isInfected()) && !p.isDead)
            {
                int n = Utilities.rng.nextInt(100);
                if (n < PlagueRun.VIRULENCE)
                {
                    this.infect();
                }
            }
        }

        if (isInfected)
        {
            timeInfected++;
        }

        if ((timeInfected >= fatalRecover) && isInfected)
        {
            if (!isDead)
            {
                PlagueRun pr = (PlagueRun) world;
                int randomOutcome = Utilities.rng.nextInt(2);
                if (randomOutcome == 0 || !pr.getFatal())
                {
                    isInfected = false;
                    isDead = false;
                }
                else
                {
                    isDead = true;
                }
            }
        }

        if (!isDead)
        {
            int dx = Utilities.rng.nextInt(11) - 5;
            int dy = Utilities.rng.nextInt(11) - 5;
            setX((getX() + dx + World.size) % World.size);
            setY((getY() + dy + World.size) % World.size);
        }

//        if (previousStatus != isInfected)
//        {
//            world.updateStatistics();
//        }
    }

    private void setColor(Color color)
    {
        this.setColor(color);
    }
}