package greed;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;

public class Cow extends MobileAgent {

    int energy;
    int greediness;
    Patch location;
    int moveEnergy;
    Meadow meadow;

    Boolean alive;

    public Cow(String name, int energy, int greediness, int moveEnergy, Meadow meadow) {
        super(name);
        this.energy = energy;
        this.greediness = greediness;
        this.moveEnergy = moveEnergy;
        this.meadow = meadow;
        this.alive = true;

        int x = Utilities.rng.nextInt(Meadow.dimX);
        int y = Utilities.rng.nextInt(Meadow.dimY);
        setX(x);
        setY(y);
        updateLocation();
    }

    public synchronized int getEnergy(){
        return energy;
    }

    public synchronized void updateEnergy(int amt){
        energy += amt;
        if (energy > 100) energy = 100;
    }


    public void update() {
        location.eatMe(this, greediness);
    }

    public void setHeading(Heading heading){
        this.heading = heading;
    }

    public void updateLocation(){
        location = meadow.field[getY()][getX()];
    }

    @Override
    public void move(int steps) {
        if (heading == Heading.NORTH){
            int y = getY();
            y -= steps;
            if (y < 0){
                y = Meadow.dimY + y;
            }
            setY(y);
        } else if (heading == Heading.SOUTH){
            int y = getY();
            y += steps;
            if (y > Meadow.dimY-1){
                y = y - Meadow.dimY;
            }
            setY(y);
        } else if (heading == Heading.WEST){
            int x = getX();
            x -= steps;
            if (x < 0){
                x = Meadow.dimX + x;
            }
            setX(x);
        } else if (heading == Heading.EAST){
            int x = getX();
            x += steps;
            if (x > Meadow.dimX-1){
                x = x - Meadow.dimX;
            }
            setX(x);
        }
    }
}
