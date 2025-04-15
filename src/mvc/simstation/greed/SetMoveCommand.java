package mvc.simstation.greed;
import mvc.*;
import mvc.simstation.World;

public class SetMoveCommand extends Command {

    World model;
    String type;
    Object source;
    Integer value = null;

    public SetMoveCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
        this.value = value;
    }

    public void execute() throws Exception{
        if (value == null){
            String response = Utilities.ask("Move Energy (0-50): ");
            value = Integer.valueOf(response);
            if (value > 50){value = 50;}
            if (value < 0){value = 0;}
        }
        Meadow m = (Meadow) model;
        m.updateMoveEnergy(value);
    }
}

