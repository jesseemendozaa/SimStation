package mvc.simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;

public class World extends Model
{
    public static final int size = 500;
    private int clock = 0;
    private int alive = 0;

    private int population = 0;
    private boolean stopped = false;
    private boolean paused = false;

    protected ArrayList<Agent> agents;
    protected ObserverAgent observer;

    public World()
    {
        super();
        agents = new ArrayList<>();
        observer = new ObserverAgent("Observer");
        observer.world = this;
        agents.add(observer);
        clock = 0;
        population = 0;
    }

    public void populate()
    {

    }

    public void addAgent(Agent a)
    {
        a.setManager(this);
        agents.add(a);
    }

    public ArrayList<Agent> getAgents()
    {
        return agents;
    }

    public void startAgents()
    {
        if (!paused && !stopped)
        {
            populate();

            for (Agent a : agents)
            {
                Thread thread = new Thread(a);
                thread.start();
            }

            changed();

        }
        else
        {
            Utilities.error("World already started. Create another file to start a new model.");
        }
    }

    public void stopAgents()
    {
        for (Agent a : agents)
        {
            a.stop();
        }

        stopped = true;
        changed();
    }

    public void pauseAgents()
    {
        for (Agent a : agents)
        {
            a.pause();
        }

        paused = true;
        changed();
    }

    public void resumeAgents()
    {
        for (Agent a : agents)
        {
            a.resume();
        }

        paused = false;
        changed();
    }

    public String[] getStatus()
    {
        return null;
    }

    public int getClock()
    {
        return clock;
    }

    public int getAlive()
    {
        return alive;
    }

    public int getPopulation()
    {
        return population;
    }

    public Agent getNeighbor(Agent agent, int radius)
    {
        int randomIndex = Utilities.rng.nextInt(agents.size());

        for (int i = 0; i < agents.size(); i++)
        {
            int index = (randomIndex + i) % agents.size();
            Agent compare = agents.get(index);

            if (compare == agent)
            {
                continue;
            }

            // Only return MobileAgents:
            if (!(compare instanceof MobileAgent))
            {
                continue;
            }

            int xDistance, yDistance;

            if (compare.getX() > agent.getX())
            {
                xDistance = Math.min(compare.getX() - agent.getX(),
                        agent.getX() + World.size - compare.getX());
            }
            else
            {
                xDistance = Math.min(agent.getX() - compare.getX(),
                        compare.getX() + World.size - agent.getX());
            }

            if (compare.getY() > agent.getY())
            {
                yDistance = Math.min(compare.getY() - agent.getY(),
                        agent.getY() + World.size - compare.getY());
            }
            else
            {
                yDistance = Math.min(agent.getY() - compare.getY(),
                        compare.getY() + World.size - agent.getY());
            }

            int totalDistance = (int) Math.sqrt((xDistance*xDistance) + (yDistance*yDistance));

            if (totalDistance < radius) {return compare;}
        }

        return null;
    }

    public void updateStatistics()
    {
        alive = 0;
        population = 0;
        clock++;

        for (Agent a : agents)
        {
            if (!a.getName().equals("Observer"))
            {
                if (!a.isStopped())
                {
                    alive++;
                }

                population++;
            }
        }

        changed();
    }
}
