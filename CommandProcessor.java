public class CommandProcessor {

    private Room currentRoom;
    private final GUIPrinter guiPrinter;

    public CommandProcessor(Room currentRoom, GUIPrinter guiPrinter){
        this.currentRoom = currentRoom;
        this.guiPrinter = guiPrinter;
    }

    public boolean processCommand(CommandParser commandParser)
    {
        boolean wantToQuit = false;

        Command command = commandParser.getCommand();

        if(command.isUnknown()) {
            guiPrinter.printUnknownCommandMessage();
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help"))
            guiPrinter.printHelp(commandParser.getValidCommands());
        else if (commandWord.equals("go"))
            goRoom(command);
        else if (commandWord.equals("quit"))
            wantToQuit = quit(command);

        return wantToQuit;
    }

    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            guiPrinter.printNoDirectionMessage();
            return;
        }

        String direction = command.getSecondWord();

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
        if(command.hasSecondWord()) {
            guiPrinter.printInvalidQuitMessage();
            return false;
        }
        else {
            return true;
        }
    }
}
