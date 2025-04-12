package plague;

import simstation.*;
import mvc.*;

public class PlagueRun extends World
{

    public static int VIRULENCE = 50;   // % chance of infection
    public static int RESISTANCE = 2;   // % chance of resisting infection
    public static int fatalRecovery = 200;

    public int populationSize = 100;
    private int initialInfectedPercent = 10;

    public PlagueRun()
    {
        super();
    }

    public void setInitialInfectedPercent(int value)
    {
        this.initialInfectedPercent = value;
    }

    public int getInitialInfectedPercent() {
        return initialInfectedPercent;
    }

    public void setInfectionProbability(int value)
    {
        this.VIRULENCE = value;
    }

    public void setInitialPopulationSize(int value)
    {
        this.populationSize = value;
    }

    public void setFatalRecovery(int value)
    {
        this.fatalRecovery = value;
    }

    public String[] getStatus()
    {
        String cl = "Clock: " + getClock();
        String al = "Alive: " + getAlive();
        String infected = "% Infected: " + getInfected();

        return new String[] {cl, al, infected};
    }

    public double getInfected()
    {
        double numInfected = 0;
        double numAlive = 0;
        for (Agent a : getAgents())
        {
            if (a.getName().equals("Observer"))
            {
                continue;
            }
            Plague p = (Plague) a;
            numAlive++;
            if (p.isInfected())
            {
                numInfected++;
            }
        }
        return (numInfected / numAlive) * 100;
    }

    @Override
    public void populate()
    {
        int numInfected = (int) (populationSize * (initialInfectedPercent / 100.0));

        for (int i = 0; i < populationSize; i++)
        {
            Plague p = new Plague(fatalRecovery);

            if (i < numInfected)
            {
                p.infect();
            }
            addAgent(p);
        }
    }

    public static void main(String[] args)
    {
        WorldFactory factory = new PlagueFactory();
        AppPanel panel = new WorldPanel(factory);
        panel.display();
    }
}