package simstation;

import mvc.*;

import java.io.Serializable;

public abstract class Agent implements Runnable, Serializable {

    protected String name;
    protected transient Thread myThread;
    private boolean paused, stopped;
    protected World world;
    private int xc;
    private int yc;

    public Agent(String name) {
        this.name = name;
        paused = false;
        stopped = false;
        myThread = null;
        this.xc = Utilities.rng.nextInt(World.size)+1;
        this.yc = Utilities.rng.nextInt(World.size)+1;
    }

    public void setManager(World w) { world = w; }
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

    public synchronized String toString() {
        String result = name;
        if (stopped) result += " (stopped)";
        else if (paused) result += " (suspended)";
        else result += " (running)";
        return result;
    }

    // thread stuff:
    public synchronized void stop() { stopped = true; }
    public synchronized boolean isStopped() { return stopped; }
    public synchronized void pause() { paused = true; }
    public synchronized boolean isPaused() { return paused;  }
    public synchronized void resume() { notify(); }


    // wait for me to die:
    public synchronized void join() {
        try {
            if (myThread != null) myThread.join();
        } catch(InterruptedException e) {
            Utilities.error(e.getMessage());
        }
    }
    // wait for notification if I'm not stopped and I am suspended
    private synchronized void checkSuspended() {
        try {
            while(!stopped && paused) {
                wait();
                paused = false;
            }
        } catch (InterruptedException e) {
            Utilities.error(e.getMessage());
        }
    }


    public void run() {
        myThread = Thread.currentThread();
        while (!isStopped()) {
            try {
                update();
                Thread.sleep(20);
                checkSuspended();
            } catch(InterruptedException e) {
                Utilities.error(e.getMessage());
            }
        }
    }

    public abstract void update();
}