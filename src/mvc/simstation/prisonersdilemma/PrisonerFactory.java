package mvc.simstation.prisonersdilemma;

import mvc.simstation.*;
import mvc.*;

//
public class PrisonerFactory extends WorldFactory
{
    public Model makeModel()
    {
        return new PrisonerSimulation();
    }

    public String getTitle()
    {
        return "Prisoner's Dilemma Tournament";
    }

    // Is this necessary?
    @Override
    public String[] getHelp()
    {
        return new String[]
                {

                };
    }
}
