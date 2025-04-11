package greed;

import mvc.AppPanel;
import simstation.*;

public class Meadow extends World{

    int waitPenalty = 5;
    int greed = 50, grow = 1, move = 10;
    static int numCows = 50;
    int dim = World.size / Patch.patchSize;
    Patch[][] field = new Patch[dim][dim];

    public Meadow(){
        super();
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                field[i][j] = new Patch("Patch", j, i);
            }
        }
    }

    public void updateGreed(int greed){
        for (Agent a : agents){
            if (a.getName().equals("Observer")){
                continue;
            }
            Cow c = (Cow) a;
            c.greediness = greed;
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
            Cow c = (Cow) a;
            c.moveEnergy = moveEnergy;
        }
        setMove(moveEnergy);
        changed();
    }

    public void populate() {
        for(int i = 0; i < numCows; i++) {
            this.addAgent(new Cow("Cow", 100, 50, 1));
        }
    }

    public static void main(String[] args) {
        AppPanel panel = new GreedPanel(new GreedFactory());
        panel.display();
    }

}
