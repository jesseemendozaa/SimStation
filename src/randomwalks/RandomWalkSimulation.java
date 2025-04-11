package randomwalks;

import simstation.*;
import mvc.*;

public class RandomWalkSimulation extends World
{
    public void populate()
    {
        for(int i = 0; i < 25; i++)
        {
            this.addAgent(new Drunk());
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
        AppPanel panel = new WorldPanel(new RandomWalkFactory());
        panel.display();
    }

}
