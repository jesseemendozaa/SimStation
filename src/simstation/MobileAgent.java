package simstation;

public class MobileAgent extends Agent{

    protected Heading heading;

    public MobileAgent(String name){
        super(name);
        heading = Heading.random();
    }

    public void update(){

    }

    public void move(int steps){
        if (heading == Heading.NORTH){
            int y = getY();
            y -= steps;
            if (y < 0){
                y = World.size + y;
            }
            setY(y);
        } else if (heading == Heading.SOUTH){
            int y = getY();
            y += steps;
            if (y > World.size){
                y = y - World.size;
            }
            setY(y);
        } else if (heading == Heading.WEST){
            int x = getX();
            x -= steps;
            if (x < 0){
                x = World.size + x;
            }
            setX(x);
        } else if (heading == Heading.EAST){
            int x = getX();
            x += steps;
            if (x > World.size){
                x = x - World.size;
            }
            setX(x);
        }
    }

    public void turn(Heading h){
        this.heading = h;
    }

}
