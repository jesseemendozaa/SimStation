package mvc.simstation.flocking;

import mvc.AppPanel;
import mvc.simstation.*;
import mvc.simstation.randomwalks.RandomWalkFactory;

public class FlockingSimulation extends World
{
    public void populate()
    {
        for (int i = 0; i < 25; i++)
        {
            this.addAgent(new Bird());
        }
    }

    @Override
    public String[] getStatus()
    {
        String cl = "Clock: " + getClock();
        String al = "Alive: " + getAlive();
        String pop = "Population " + getPopulation();

        return new String[] {cl, al, pop};
    }

    public static void main(String[] args)
    {
        AppPanel panel = new WorldPanel(new FlockingFactory());
        panel.display();
    }
}
