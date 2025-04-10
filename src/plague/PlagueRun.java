package plague;

import simstation.*;
import mvc.*;

public class PlagueRun extends World
{

    public static int VIRULENCE = 50;   // % chance of infection (updated by slider)
    public static int RESISTANCE = 2;   // % chance of resisting infection
    public static int fatalRecovery = 200;

    private int populationSize = 100;
    private int initialInfectedPercent = 10;

    public PlagueRun()
    {
        super();
    }

    public void UpdatedSimValues(PlagueView view)
    {
        this.populationSize = view.getPopulationSize();
        this.initialInfectedPercent = view.getInitialInfected();
        VIRULENCE = view.getInfectionProbability();
        fatalRecovery = view.getFatalityTime();
    }

    @Override
    public void populate()
    {
        int numInfected = (int) (populationSize * (initialInfectedPercent / 100.0));

        for (int i = 0; i < populationSize; i++)
        {
            Plague p = new Plague();

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