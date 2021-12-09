public class CommandWords {
    private static final String[] validCommands = {"go", "quit", "help"};

    public CommandWords(){

    }

    public boolean isCommand(String command) {
        for(int i = 0; i < validCommands.length; i++) {
            if(validCommands[i].equals(command))
                return true;
        }

        return false;
    }

    public static String[] getValidCommands() {
        return validCommands;
    }
}
