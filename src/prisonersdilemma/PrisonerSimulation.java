package prisonersdilemma;

import simstation.*;
import mvc.*;

public class PrisonerSimulation extends World
{
    // Using 40 prisoners at start so that we can have 10 of each strategy.
    public static int PRISONER_POPULATION = 40;

    @Override
    public void populate()
    {
        /*
            Since I didn't use enumerations to represent the types of strategies, I will be using integers instead.

                (0) -> Cheat
                (1) -> Cooperate
                (2) -> RandomlyCooperate
                (3) -> Tit4Tat
         */
        int strategy;
        Prisoner prisoner;

        for (int i = 0; i < PRISONER_POPULATION; i++)
        {
            strategy = i % 4;

            if (strategy == 0)
            {
                prisoner = new Prisoner(new Cheat(), "Cheater");
            }
            else if (strategy == 1)
            {
                prisoner = new Prisoner(new Cooperate(), "Cooperator");
            }
            else if (strategy == 2)
            {
                prisoner = new Prisoner(new RandomlyCooperate(), "Gambler");
            }
            else
            {
                prisoner = new Prisoner(new Tit4Tat(), "Copycat");
            }

            prisoner.getStrategy().setPrisoner(prisoner);
            addAgent(prisoner);
        }
    }

    // Statistics need to be stored:
    @Override
    public String[] getStatus()
    {
        // int cheatTotal = 0;
        int cheatAverage = 0;
        // int cooperateTotal = 0;
        int cooperateAverage = 0;
        // int randomlyCooperateTotal = 0;
        int randomlyCooperateAverage = 0;
        // int tit4tatTotal = 0;
        int tit4tatAverage = 0;


        // Loop through the prisoners and calculate the average of each strategy:
        for (Agent prisoner : agents) {
            // Need to cast agent as prisoner:
            if (prisoner instanceof MobileAgent) {
                Prisoner currentPrisoner = (Prisoner) prisoner;
                if (currentPrisoner.getName().equals("Cheater")) {
                    cheatAverage = cheatAverage + currentPrisoner.getFitness();
                } else if (currentPrisoner.getName().equals("Cooperator")) {
                    cooperateAverage = cooperateAverage + currentPrisoner.getFitness();
                } else if (currentPrisoner.getName().equals("Gambler")) {
                    randomlyCooperateAverage = randomlyCooperateAverage + currentPrisoner.getFitness();
                } else {
                    tit4tatAverage = tit4tatAverage + currentPrisoner.getFitness();
                }
            }
        }

        // Since each strategy has exactly 10 prisoners (they never change their strategy), we can just divide by 10.
        cheatAverage = cheatAverage / 10;
        cooperateAverage = cooperateAverage / 10;
        randomlyCooperateAverage = randomlyCooperateAverage / 10;
        tit4tatAverage = tit4tatAverage / 10;

        return new String[]
                {
                        //"Total Cheat strategy fitness: " + cheatTotal,
                        //"Total Cooperate strategy fitness: " + cooperateTotal,
                        //"Total Randomly Cooperate strategy fitness: " + randomlyCooperateTotal,
                        //"Total Tit4Tat strategy fitness: " + tit4tatTotal,
                        "Average Cheat strategy fitness: " + cheatAverage,
                        "Average Cooperate strategy fitness: " + cooperateAverage,
                        "Average Randomly Cooperate strategy fitness: " + randomlyCooperateAverage,
                        "Average Tit4Tat strategy fitness: " + tit4tatAverage
                };
    }

    // Main function to run simulation.
    public static void main(String[] args)
    {
        AppPanel panel = new WorldPanel(new PrisonerFactory());
        panel.display();
    }
}
