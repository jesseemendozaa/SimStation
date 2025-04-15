package plague;

import mvc.*;
import simstation.*;

import javax.swing.*;
import java.awt.*;

public class SetInitialPopulationSize extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetInitialPopulationSize(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Initial Population Size(0-200): ");
            value = Integer.valueOf(response);
            if (value > 200) { value = 200; }
            if (value < 0) { value = 0; }
        }

        PlagueRun m = (PlagueRun) model;
        m.setInitialPopulationSize(value);

        model.changed();
    }
}