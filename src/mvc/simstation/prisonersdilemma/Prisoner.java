package mvc.simstation.prisonersdilemma;

import mvc.simstation.*;
import mvc.*;

// Using "MobileAgent" instead of "Agent" so that we have access to "Heading" & "move".
public class Prisoner extends MobileAgent
{
    /*
        (1) Each prisoner (agent) has a fitness score initially set to 0.
        (2) The update function is called when playing a round of prisoner's dilemma with a random neighbor.
        (3) Each strategy can be functionally grouped into either a cooperate or cheat category:
            - Always cheat.
            - Always cooperate.
            - Randomly cooperate.
            - Tit-for-tat.
        (4) The starting population of prisoners should be divided evenly into quarters, with each strategy making up one quarter.
        (5) Statistics box should display the average fitness for each strategy.
     */

    private int fitness = 0;
    private boolean partnerCheated;

    private Strategy strategy;

    public Prisoner(Strategy strategy, String name)
    {
        super(name);
        this.strategy = strategy;
        strategy.setPrisoner(this); // Not sure if this is necessary.
        this.heading = Heading.random();
    }

    // Checks to see whether a prisoner is choosing to cheat or cooperate.
    public boolean cooperate()
    {
        return strategy.cooperate();
    }

    // Next pair of getters and setters is used for the "Tit4Tat" strategy calculation.
    public boolean getLastPartnerCheated()
    {
        return partnerCheated;
    }

    public void setLastPartnerCheated(boolean cheated)
    {
        this.partnerCheated = cheated;
    }

    // Used to update each prisoner's fitness score after a round.
    public void updateFitness(int amt)
    {
        fitness = fitness + amt;
    }

    // Used later to display statistics.
    public int getFitness()
    {
        return this.fitness;
    }

    // Used later to display statistics.
    public Strategy getStrategy()
    {
        return this.strategy;
    }

    /*
        Simulates the prisoner's dilemma tournament gameplay logic:

            (1) Prisoner A moves in a random direction to search for their next opponent.
            (2) If an opponent is found, fitness points are added to each prisoner depending on their choice of strategy.
            (3) Each prisoner will remember what their last opponent's strategy is (for "Tit4Tat" purposes).
     */
    @Override
    public void update()
    {
        heading = Heading.random();
        int steps = Utilities.rng.nextInt(10) + 1;
        move(steps);

        Agent neighbor = world.getNeighbor(this, 10);

        if (neighbor instanceof Prisoner)
        {
            Prisoner opponent = (Prisoner) neighbor;

            boolean firstPrisonerChoice = this.getStrategy().cooperate();
            boolean secondPrisonerChoice = opponent.getStrategy().cooperate();

            if (firstPrisonerChoice && secondPrisonerChoice) // Both cooperate, +3 fitness to both.
            {
                this.updateFitness(3);
                opponent.updateFitness(3);
            } else if (!firstPrisonerChoice && secondPrisonerChoice) // I cheat, my opponent cooperates, +5 fitness to me.
            {
                this.updateFitness(5);
                opponent.updateFitness(0);
            } else if (firstPrisonerChoice && !secondPrisonerChoice) // I cooperate, my opponent cheats, +5 fitness to opponent.
            {
                this.updateFitness(0);
                opponent.updateFitness(5);
            } else // Both of us cheat, +1 fitness to both.
            {
                this.updateFitness(1);
                opponent.updateFitness(1);
            }

            // Save results of opponent's choice for Tit4Tat:
            this.setLastPartnerCheated(secondPrisonerChoice);
        }
    }
}
