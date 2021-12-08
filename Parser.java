import java.util.Scanner;
public class Parser {
    private Scanner reader;
    private CommandWords commands;
    public Parser()
    {
        reader = new Scanner(System.in);
    }
    public Commands getCommand()
    {
        String inputLine;
        String word_action = null;
        String word_direction = null;

        System.out.print(" > ");

        inputLine = reader.nextLine();

        Scanner inputData = new Scanner(inputLine);
        while(inputData.hasNext()){
            word_action = inputData.next();
        if(inputData.hasNext()) {
            word_direction = inputData.next();
        }
    }


        if(commands.isCommand(word_action)) {
            return new Commands(word_action, word_direction);
        }
        else {
            return new Commands(null, word_direction);
        }
    }
}



