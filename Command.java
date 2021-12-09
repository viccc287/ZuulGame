public class Command {
    private final String actionWord;
    private final String directionWord;

    public Command(String action, String direction) {
        actionWord = action;
        this.directionWord = direction;
    }

    public String getActionWord()
    {
        return actionWord;
    }

    public String getDirectionWord()
    {
        return directionWord;
    }

    public boolean isUnknown()
    {
        return (actionWord == null);
    }

    public boolean hasDirectionWord()
    {
        return (directionWord != null);
    }
}
