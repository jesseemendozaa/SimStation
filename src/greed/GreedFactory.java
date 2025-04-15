package greed;

import simstation.WorldFactory;
import mvc.*;

import javax.swing.*;

public class GreedFactory extends WorldFactory {

    public Model makeModel() {
        return new Meadow();
    }

    public View makeView(Model m) {
        return new GreedView((Meadow) m);
    }

    public String getTitle() {
        return "Greed Simulator";
    }

    public String[] getEditCommands(){
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats", "Greed", "Grow Back Rate", "Move Energy"};
    }

    public String[] getHelp(){
        return new String[] {"Press Start to Begin",
                "Once the simulation is stopped, it cannot be started/resumed again",
                "The simulation cannot be saved after it has been started",
                "Greed: Control how much energy each cow wants to eat at each patch of grass",
                "Grow Back Rate: Control how for each patch recovers energy",
                "Move Energy: Control how much energy the cows uses to move",
                "Red dots are alive cows, White dots are dead cows"};
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
