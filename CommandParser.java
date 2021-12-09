import java.util.Scanner;
public class CommandParser {
    private final Scanner reader;
    private final CommandWords commands;
    public CommandParser()
    {
        commands = CommandWords.getInstance();
        reader = new Scanner(System.in);
    }
    public Command getCommand() {
        String word_action = null;
        String word_direction = null;
        System.out.print(" > ");
        parsingMethod();
        if(commands.isCommand(word_action)) {
            return new Command(word_action, word_direction);
        } else {
            return new Command(null, word_direction);
        }
    }
    private void parsingMethod(){
        String inputLine;
        inputLine = reader.nextLine();
        Scanner inputData = new Scanner(inputLine);
        while(inputData.hasNext()){
            String word_action = inputData.next();
            if(inputData.hasNext()) {
                String word_direction = inputData.next();
            }
        }
    }

    public String[] getValidCommands(){
        return commands.getValidCommands();
    }

}



