package prisonersdilemma;

/*
    There are four strategy options:
        (1) Cooperate
        (2) Randomly cooperate
        (3) Cheat
        (4) Tit4Tat
            - Only cooperate if the last opponent cooperated.

    Review the table in the assignment page for how fitness points should be distributed.
 */

public abstract class Strategy
{
    protected Prisoner prisoner;
    public abstract boolean cooperate();

    protected void setPrisoner(Prisoner prisoner)
    {
        this.prisoner = prisoner;
    }
}

