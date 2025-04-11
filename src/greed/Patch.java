package greed;
import simstation.*;

public class Patch extends Agent{

    int growBackRate = 1;
    static int patchSize = 10;
    int x,y;

    public Patch(String name, int x, int y){
        super(name);
        int energy = 100;
        this.x = x;
        this.y = y;
    }

    public void update(){

    }

}
