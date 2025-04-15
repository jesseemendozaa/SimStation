package plague;

import simstation.*;
import mvc.*;

import java.awt.*;

public class Plague extends MobileAgent
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
                int r = Utilities.rng.nextInt(2);
                if (r == 0 || !pr.getFatal())
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
            move(1);
        }
    }

    private void setColor(Color color)
    {
        this.setColor(color);
    }
}