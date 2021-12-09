public class Commands {
    private final String commandWord;
    private final String secondWord;

    public Commands(String action, String direction) {
        commandWord = action;
        this.secondWord = direction;
    }

    public String getCommandWord()
    {
        return commandWord;
    }

    public String getSecondWord()
    {
        return secondWord;
    }

    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
