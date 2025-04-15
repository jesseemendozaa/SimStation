package mvc.simstation.plague;

import mvc.*;
import mvc.simstation.*;
import javax.swing.*;

public class PlagueFactory extends WorldFactory
{

    @Override
    public Model makeModel()
    {
        return new PlagueRun();
    }

    @Override
    public View makeView(Model model)
    {
        return new PlagueView(model);
    }

    @Override
    public String getTitle()
    {
        return "Plague";
    }

    @Override
    public String[] getHelp()
    {
        return new String[]{"Click on the buttons below to get started"};
    }

    @Override
    public String about()
    {
        return "SimStation group 3";
    }

    @Override
    public String[] getEditCommands()
    {
        return new String[]{"Start", "Pause", "Resume", "Stop", "Stats", "Initial % Infected", "Infection Probability", "Initial Population Size", "Fatality/Recovery Time", "Not Fatal"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source)
    {
        Command cmmd = super.makeEditCommand(model, type, source);

        if (cmmd == null)
        {
            if (type.equals("Initial % Infected"))
            {
                cmmd = new SetInitialInfectedCommand(model, type, source);
                if (source instanceof JSlider)
                {
                    ((SetInitialInfectedCommand) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Infection Probability"))
            {
                cmmd = new SetInfectionProbability(model, type, source);
                if (source instanceof JSlider)
                {
                    ((SetInfectionProbability) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Initial Population Size"))
            {
                cmmd = new SetInitialPopulationSize(model, type, source);
                if (source instanceof JSlider)
                {
                    ((SetInitialPopulationSize) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Fatality/Recovery Time"))
            {
                cmmd = new SetFatalRecovery(model, type, source);
                if (source instanceof JSlider)
                {
                    ((SetFatalRecovery) cmmd).value = ((JSlider) source).getValue();
                }
            }
            else if (type.equals("Not Fatal")){
                cmmd = new NotFatalCommand(model, type, source);
            }
        }

        return cmmd;
    }
}