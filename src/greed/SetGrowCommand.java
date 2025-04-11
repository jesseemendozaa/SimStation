package greed;
import mvc.*;
import simstation.World;

import javax.swing.event.MouseInputListener;

public class SetGrowCommand extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetGrowCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Grow Back Rate Amount (0-10): ");
            value = Integer.valueOf(response);
            if (value > 10){value = 10;}
            if (value < 0){value = 0;}
        }
        Meadow m = (Meadow) model;
        m.updateGrow(value);
    }
}

