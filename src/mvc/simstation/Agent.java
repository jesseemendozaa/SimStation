package mvc.simstation;

import mvc.*;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable
{
    protected String name;
    protected transient Thread myThread;
    private boolean paused, stopped;
    protected World world;
    private int xc;
    private int yc;

    // Agent constructor:
    public Agent(String name)
    {
        this.name = name;
        paused = false;
        stopped = false;
        myThread = null;
        this.xc = Utilities.rng.nextInt(World.size)+1;
        this.yc = Utilities.rng.nextInt(World.size)+1;
    }

    // Getters and setters:
    public String getName() { return name; }

    public int getX(){
        return xc;
    }

    public int getY(){
        return yc;
    }

    public void setX(int x){
        this.xc = x;
    }

    public void setY(int y){
        this.yc = y;
    }

    // An agent belongs to a world (A.K.A. its manager):
    public void setManager(World w)
    {
        this.world = w;
    }

    public synchronized void start()
    {
        myThread = new Thread(this);
        myThread.start();
    }

    // thread stuff:
    public synchronized void stop() { stopped = true; }
    public synchronized boolean isStopped() { return stopped; }
    public synchronized void pause() { paused = true; }
    public synchronized boolean isPaused() { return paused;  }
    public synchronized void resume() { notify(); }


    // wait for me to die:
    public synchronized void join()
    {
        try
        {
            if (myThread != null) myThread.join();
        }
        catch(InterruptedException e)
        {
            Utilities.error(e.getMessage());
        }
    }

    // wait for notification if I'm not stopped and I am suspended
    private synchronized void checkSuspended()
    {
        try
        {
            while(!stopped && paused)
            {
                wait();
                paused = false;
            }
        }
        catch (InterruptedException e)
        {
            Utilities.error(e.getMessage());
        }
    }

    public void run()
    {
        onStart();
        myThread = Thread.currentThread();

        while (!isStopped())
        {
            try
            {
                update();
                Thread.sleep(20);
                checkSuspended();
            }
            catch(InterruptedException e)
            {
                onInterrupted();
                Utilities.error(e.getMessage());
            }
        }
        onExit();
        world.changed();
    }

    // Empty methods that can be overridden in subclasses:
    public void onStart()
    {

    }

    public void onInterrupted()
    {

    }

    public void onExit()
    {

    }

    public synchronized String toString()
    {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (paused) result += " (suspended)";
        else result += " (running)";
        return result;
    }

    public abstract void update();
}