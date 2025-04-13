package greed;

import mvc.Utilities;
import simstation.Heading;
import simstation.MobileAgent;
import simstation.World;

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
        heading = Heading.random();
        energy -= Meadow.waitPenalty;
        if (this.energy < 100) {
            if (location.getEnergy() >= greediness) {
                location.eatMe(this, greediness);
            } else {
                if (this.energy > moveEnergy){
                    int steps = Utilities.rng.nextInt(2) + 1;
                    move(steps);
                    energy -= moveEnergy;
                }
            }
        }
        if (this.energy <= 0){
            this.alive = false;
            stop();
        }

        updateLocation();
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
