public class GUIPrinter {
    public void printWelcome(Room currentRoom)
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printCurrentLocation(currentRoom);

    }

    public void printCurrentLocation(Room currentRoom){
        System.out.println("You are " + currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.getNorthExit() != null) {
            System.out.print("north ");
        }
        if(currentRoom.getEastExit() != null) {
            System.out.print("east ");
        }
        if(currentRoom.getSouthExit() != null) {
            System.out.print("south ");
        }
        if(currentRoom.getWestExit() != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    public void printHelp()
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    public void printGoodbye(){
        System.out.println("Thank you for playing.  Good bye.");
    }

    public void printUnknownCommandMessage(){
        System.out.println("I don't know what you mean...");
    }

    public void printNoDirectionMessage(){
        System.out.println("Go where?");
    }

    public void printInvalidExitMessage(){
        System.out.println("There is no door!");
    }

    public void printPrompt(){
        System.out.println("> ");
    }

}
