public class CommandProcessor {

    private Room currentRoom;
    private final GUIPrinter guiPrinter;

    public CommandProcessor(Room currentRoom, GUIPrinter guiPrinter){
        this.currentRoom = currentRoom;
        this.guiPrinter = guiPrinter;
    }

    public boolean processCommand(Parser parser)
    {
        boolean wantToQuit = false;

        Command command = parser.getCommand();

        if(command.isUnknown()) {
            guiPrinter.printUnknownCommandMessage();
            return false;
        }

        String commandWord = command.getActionWord();
        if (commandWord.equals("help"))
            guiPrinter.printHelp(parser.getValidCommands());
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
    }

    private void goRoom(Command command)
    {
        if(!command.hasDirectionWord()) {
            guiPrinter.printNoDirectionMessage();
            return;
        }

        String direction = command.getDirectionWord();

        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getNorthExit();
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.getEastExit();
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.getSouthExit();
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.getWestExit();
        }

        if (nextRoom == null) {
            guiPrinter.printInvalidExitMessage();
        }
        else {
            currentRoom = nextRoom;
            guiPrinter.printCurrentLocation(currentRoom);
        }



    }

    private boolean quit(Command command)
    {
        if(command.hasDirectionWord()) {
            guiPrinter.printInvalidQuitMessage();
            return false;
        }
        else {
            return true;
        }
    }
}
