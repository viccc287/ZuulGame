public class CommandWords {

    private static CommandWords instance;

    private final String[] validCommands = {"go", "quit", "help"};

    private CommandWords(){

    }

    public static CommandWords getInstance() {
        if (instance == null) return new CommandWords();
        else return instance;
    }

    public boolean isCommand(String command) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(command))
                return true;
        }

        return false;
    }

    public String[] getValidCommands() {
        return validCommands;
    }
}
