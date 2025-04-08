package simstation;

import mvc.Model;
import mvc.Utilities;

import java.util.ArrayList;

public class World extends Model {

    public static final int size = 500;
    private int clock = 0;
    private int alive = 0;
    private int population = 0;
    private boolean stopped = false;
    private boolean paused = false;

    protected ArrayList<Agent> agents;
    protected ObserverAgent observer;

    public World() {
        super();
        agents = new ArrayList<>();
        observer = new ObserverAgent("Observer");
        observer.world = this;
        agents.add(observer);
        clock = 0;
        population = 0;
    }

    public void populate(){
    }

    public void addAgent(Agent a){
        a.setManager(this);
        agents.add(a);
    }

    public ArrayList<Agent> getAgents(){
        return agents;
    }

    public void startAgents(){
        if (!paused && !stopped){
            populate();
            for (Agent a : agents){
                Thread thread = new Thread(a);
                thread.start();
            }
            changed();
        } else {
            Utilities.error("World already started. Create another file to start a new model.");
        }
    }

    public void stopAgents(){
        for (Agent a : agents){
            a.stop();
        }
        stopped = true;
        changed();
    }

    public void pauseAgents(){
        for (Agent a : agents){
            a.pause();
        }
        paused = true;
        changed();
    }

    public void resumeAgents(){
        for (Agent a : agents){
            a.resume();
        }
        paused = false;
        changed();
    }

    public String[] getStatus(){
        return null;
    }

    public int getClock(){
        return clock;
    }

    public int getAlive(){
        return alive;
    }

    public int getPopulation(){
        return population;
    }

    public void updateStatistics(){
        alive = 0;
        population = 0;
        clock ++;
        for (Agent a : agents){
            if (!a.getName().equals("Observer")) {
                if (!a.isStopped()) {
                    alive++;
                }
                population++;
            }
        }
        changed();
    }

}
