package plague;

import mvc.*;
import simstation.*;

public class SetInitialInfectedCommand extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetInitialInfectedCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Initial % Infected(0-100): ");
            value = Integer.valueOf(response);
            if (value > 100) { value = 100; }
            if (value < 0) { value = 0; }
        }

        PlagueRun m = (PlagueRun) model;
        m.setInitialInfectedPercent(value);

        model.changed();
    }
}