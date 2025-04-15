package mvc.simstation;

public class ObserverAgent extends Agent
{
    public ObserverAgent(String name) {
        super(name);
    }

    public void update(){
        world.updateStatistics();
    }
}
