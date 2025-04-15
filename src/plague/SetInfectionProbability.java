package plague;

import mvc.*;
import simstation.*;

import javax.swing.*;
import java.awt.*;

public class SetInfectionProbability extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetInfectionProbability(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Set Infection Probability(0-100): ");
            value = Integer.valueOf(response);
            if (value > 100) { value = 100; }
            if (value < 0) { value = 0; }
        }

        PlagueRun m = (PlagueRun) model;
        m.setInfectionProbability(value);

        model.changed();
    }
}