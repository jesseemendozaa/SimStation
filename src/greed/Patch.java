package greed;
import mvc.Utilities;
import simstation.*;

public class Patch extends Agent{

    int energy;
    int growBackRate = 1;
    static int patchSize = 10;
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
    }

    public synchronized void eatMe(Cow cow, int amt){
        updateEnergy(-amt);
        cow.updateEnergy(amt);
    }

    public synchronized void update(){
        if (getEnergy() < 100){
            updateEnergy(growBackRate);
        }
    }

}
