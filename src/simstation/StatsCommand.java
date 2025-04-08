package simstation;

import mvc.Command;
import mvc.Model;
import mvc.Utilities;

public class StatsCommand extends Command {

    World model;
    String type;
    Object source;

    public StatsCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        Utilities.inform(model.getStatus());
    }
}
