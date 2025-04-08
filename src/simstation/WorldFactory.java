package simstation;

import mvc.*;

public class WorldFactory implements AppFactory {

    public Model makeModel() { return new World(); }

    public View makeView(Model m) {
        return new WorldView(m);
    }

    public String[] getEditCommands() {
        return new String[] {"Start", "Pause", "Resume", "Stop", "Stats"};
    }

    public Command makeEditCommand(Model model, String type, Object source) {
        try {
            switch (type) {
                case "Start":
                    return new StartCommand(model, type, source);
                case "Stop":
                    return new StopCommand(model, type, source);
                case "Resume":
                    return new ResumeCommand(model, type, source);
                case "Pause":
                    return new PauseCommand(model, type, source);
                case "Stats":
                    return new StatsCommand(model, type, source);
            }
        } catch (Exception e) {
            Utilities.error(e);
        }
        return null;
    }

    public String getTitle() { return "Simstation"; }

    public String[] getHelp() {
        return new String[] {"Press Start to Begin"};
    }

    public String about() {
        return "Simstation. Copyright 2025. Designed by Vidit Chavarkar, Jesse Mendoza, Gloria Duo";
    }

}
