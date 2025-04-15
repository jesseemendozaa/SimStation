package mvc.simstation.greed;
import mvc.Utilities;
import mvc.simstation.*;

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
    }

    public synchronized void eatMe(Cow cow, int amt){
        while (amt > getEnergy()){
            try {
                wait();
            } catch(Exception e){
                Utilities.error(e);
            }
        }
        updateEnergy(-amt);
        cow.updateEnergy(amt);
        notify();
    }

    public synchronized void update(){
        if (getEnergy() < 100){
            updateEnergy(growBackRate);
        }
    }

}
