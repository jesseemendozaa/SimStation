package mvc.simstation;

public class MobileAgent extends Agent
{
    protected Heading heading;

    public MobileAgent(String name)
    {
        super(name);
        heading = Heading.random();
    }

    public Heading getHeading()
    {
        return heading;
    }

    // Need to update after every step.
    public void move(int steps)
    {
        for (int i = 0; i < steps; i++)
        {
            if (heading == Heading.NORTH)
            {
                int y = getY();
                y--;

                if (y < 0)
                {
                    y = World.size - 1;
                }

                setY(y);
            }
            else if (heading == Heading.SOUTH)
            {
                int y = getY();
                y++;

                if (y > World.size)
                {
                    y = 0;
                }

                setY(y);
            }
            else if (heading == Heading.EAST)
            {
                int x = getX();
                x++;

                if (x > World.size)
                {
                    x = 0;
                }

                setX(x);
            }
            else
            {
                int x = getX();
                x--;

                if (x < 0)
                {
                    x = World.size - 1;
                }

                setX(x);
            }

            if (world != null)
            {
                world.changed();
            }
        }
    }

    public void turn(Heading h)
    {
        this.heading = h;
    }

    public void turnRandomly()
    {
        this.heading = Heading.random();
    }

    public void update()
    {

    }
}
