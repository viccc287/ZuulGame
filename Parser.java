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
        String word1 = null;
        String word2 = null;

        System.out.print(" > ");

        inputLine = reader.nextLine();

        Scanner tokenizer = new Scanner(inputLine);
        while(tokenizer.hasNext()){
        word1 = tokenizer.next();
        if(tokenizer.hasNext()) {
            word2 = tokenizer.next();
        }
    }


        if(commands.isCommand(word1)) {
            return new Commands(word1, word2);
        }
        else {
            return new Commands(null, word2);
        }
    }
}



