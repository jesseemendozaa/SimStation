package mvc.simstation.greed;

import mvc.AppPanel;
import mvc.simstation.*;
import java.text.DecimalFormat;

public class Meadow extends World{

    static final DecimalFormat df = new DecimalFormat("0.00");
    static int waitPenalty = 5;
    int greed = 50, grow = 1, move = 10;
    static int numCows = 50;
    static int dimX = (World.size / Patch.patchSize)+11;
    static int dimY = (World.size / Patch.patchSize)+5;
    Patch[][] field = new Patch[dimY][dimX];

    public Meadow(){
        super();
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                field[i][j] = new Patch("Patch", j, i);
            }
        }
    }

    public void updateGreed(int greed){
        for (Agent a : agents){
            if (a.getName().equals("Observer")){
                continue;
            }
            if (a.getName().equals("Cow")){
                Cow c = (Cow) a;
                c.greediness = greed;
            }
        }
        setGreed(greed);
        changed();
    }

    public void updateGrow(int grow){
        for (Patch[] pRow : field){
            for (Patch p : pRow){
                p.growBackRate = grow;
            }
        }
        setGrow(grow);
        changed();
    }


    public void setGreed(int greed){
        this.greed = greed;
    }

    public int getGreed(){
        return greed;
    }

    public void setGrow(int grow){
        this.grow = grow;
    }

    public int getGrow(){
        return grow;
    }

    public void setMove(int move){
        this.move = move;
    }

    public int getMove(){
        return move;
    }

    public void updateMoveEnergy(int moveEnergy){
        for (Agent a : agents){
            if (a.getName().equals("Observer")){
                continue;
            }
            if (a.getName().equals("Cow")){
                Cow c = (Cow) a;
                c.moveEnergy = moveEnergy;
            }
        }
        setMove(moveEnergy);
        changed();
    }

    public void populate() {
        for(int i = 0; i < numCows; i++) {
            this.addAgent(new Cow("Cow", 100, 50, 1, this));
        }
        for(Patch[] pRow : field){
            for (Patch p : pRow){
                this.addAgent(p);
            }
        }
    }

    public int getAlive(){
        int alive = 0;
        for (Agent a : agents){
            if (a.getName().equals("Cow")){
                Cow c = (Cow)a;
                if (c.alive){alive++;}
            }

        }
        return alive;
    }

    public double getAverageCowEnergy(){
        double sum = 0;
        double num = 0;
        for (Agent a : agents){
            if (a.getName().equals("Cow")){
                Cow c = (Cow)a;
                if (c.alive){
                    sum+= c.getEnergy();
                    num++;
                }
            }

        }
        return sum/num;
    }

    public double getAveragePatchEnergy(){
        double sum = 0;
        double num = 0;
        for (Agent a : agents){
            if (a.getName().equals("Patch")){
                Patch p = (Patch)a;
                sum+= p.getEnergy();
                num++;
            }

        }
        return sum/num;
    }


    public String[] getStatus(){
        String cl = "Clock: " + getClock();
        String al = "Alive: " + getAlive();
        String ce = "Average Cow Energy: " + df.format(getAverageCowEnergy());
        String pe = "Average Patch Energy: " + df.format(getAveragePatchEnergy());

        return new String[]{cl, al, ce, pe};
    }

    public static void main(String[] args) {
        AppPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }

}
