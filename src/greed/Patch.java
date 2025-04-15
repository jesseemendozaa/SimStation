package greed;
import mvc.Utilities;
import simstation.*;

public class Patch extends Agent{

    int energy;
    int growBackRate = 1;
    static int patchSize = 13;
    int x,y;

    public Patch(String name, int x, int y){
        super(name);
        energy = 100;
        this.x = x;
        this.y = y;
    }

    public synchronized int getEnergy(){
        return energy;
    }

    public synchronized void updateEnergy(int amt){
        energy += amt;
        if (energy > 100){energy = 100;}
    }

    public synchronized boolean eatMe(Cow cow, int amt){
        boolean result = false;
        while (amt > getEnergy()){
            try {
                if (cow.energy > cow.moveEnergy) {
                    cow.setHeading(Heading.random());
                    int steps = Utilities.rng.nextInt(1) + 1;
                    cow.move(steps);
                    cow.energy -= cow.moveEnergy;
                    cow.updateLocation();
                    return true;
                } else {
                    cow.energy -= Meadow.waitPenalty;
                }
                if (cow.energy <= 0){
                    cow.alive = false;
                    cow.stop();
                }
                wait();
            } catch(Exception e){
                Utilities.error(e);
            }
        }
        if (amt <= getEnergy()){
            updateEnergy(-amt);
            cow.updateEnergy(amt);
            result = true;
        }
        return result;
    }

    public synchronized void update(){
        if (getEnergy() < 100){
            updateEnergy(growBackRate);
            notifyAll();
        }
    }

}
