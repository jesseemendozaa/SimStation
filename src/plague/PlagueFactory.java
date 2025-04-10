package plague;

import mvc.*;
import simstation.*;

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
        return new String[]{"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    @Override
    public Command makeEditCommand(Model model, String type, Object source)
    {
        switch (type)
        {
            case "Start":
                return new StartCommand(model, type, source);
            case "Pause":
                return new PauseCommand(model, type, source);
            case "Resume":
                return new ResumeCommand(model, type, source);
            case "Stop":
                return new StopCommand(model, type, source);
            case "Stats":
                return new StatsCommand(model, type, source);
            default:
                return null;
        }
    }
}