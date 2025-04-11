package greed;
import mvc.*;
import simstation.World;

import javax.swing.event.MouseInputListener;

public class SetGreedCommand extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetGreedCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Greed Amount (0-100): ");
            value = Integer.valueOf(response);
            if (value > 100){value = 100;}
            if (value < 0){value = 0;}
        }
        Meadow m = (Meadow) model;
        m.updateGreed(value);
    }
}

