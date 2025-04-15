package prisonersdilemma;

public class Tit4Tat extends Strategy
{
    @Override
    public boolean cooperate()
    {
        if (prisoner.getLastPartnerCheated())
        {
            return false;
        }

        return true;
    }
}
