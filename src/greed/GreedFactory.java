package greed;

import simstation.WorldFactory;
import mvc.*;
import javax.swing.*;

public class GreedFactory extends WorldFactory {

    public Model makeModel() {
        return new Meadow();
    }

    public String getTitle() {
        return "Greed Simulator";
    }

    public String[] getEditCommands(){
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats", "Greed", "Grow Back Rate", "Move Energy"};
    }

    public Command makeEditCommand(Model model, String type, Object source){
        Command cmmd = super.makeEditCommand(model, type, source);
        if (cmmd == null) {
            if (type.equals("Greed")){
                cmmd = new SetGreedCommand(model, type, source);
                if (source instanceof JSlider){
                    ((SetGreedCommand)cmmd).value = ((JSlider)source).getValue();
                }
            } else if (type.equals("Grow Back Rate")){
                cmmd = new SetGrowCommand(model, type, source);
                if (source instanceof JSlider){
                    ((SetGrowCommand)cmmd).value = ((JSlider)source).getValue();
                }
            } else if (type.equals("Move Energy")){
                cmmd = new SetMoveCommand(model, type, source);
                if (source instanceof JSlider){
                    ((SetMoveCommand)cmmd).value = ((JSlider)source).getValue();
                }
            }
        }
        return cmmd;
    }

}
