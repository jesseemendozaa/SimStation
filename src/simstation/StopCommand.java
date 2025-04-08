package simstation;

import mvc.Command;
import mvc.Model;

public class StopCommand extends Command {

    World model;
    String type;
    Object source;

    public StopCommand(Model model, String type, Object source){
        super(model);
        this.model = (World) model;
        this.type = type;
        this.source = source;
    }

    public void execute() throws Exception{
        model.stopAgents();
    }
}
