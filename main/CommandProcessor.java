package main;

public class CommandProcessor {

    private Room currentRoom;
    private final GUIPrinter guiPrinter;

    public CommandProcessor(Room currentRoom, GUIPrinter guiPrinter) {
        this.currentRoom = currentRoom;
        this.guiPrinter = guiPrinter;
    }

    public boolean processCommand(CommandParser commandParser)
    {
        boolean wantToQuit = false;

        Command command = commandParser.getCommand();

        if (command.isUnknown()) {
            guiPrinter.printUnknownCommandMessage();
            return false;
        }

        String commandWord = command.getActionWord();

        switch (commandWord) {
            case "help":
                guiPrinter.printHelp(commandParser.getValidCommands());
                break;
            case "go":
                goRoom(command);
                break;
            case "quit":
                wantToQuit = quit(command);
                break;
        }

        return wantToQuit;
    }

    private void goRoom(Command command) {
        if (!command.hasDirectionWord()) {
            guiPrinter.printNoDirectionMessage();
            return;
        }

        String direction = command.getDirectionWord();

        Room nextRoom = null;

        switch (direction) {
            case "north":
                nextRoom = currentRoom.getNorthExit();
                break;
            case "east":
                nextRoom = currentRoom.getEastExit();
                break;
            case "south":
                nextRoom = currentRoom.getSouthExit();
                break;
            case "west":
                nextRoom = currentRoom.getWestExit();
                break;
        }

        if (nextRoom == null) {
            guiPrinter.printInvalidExitMessage();
        } else {
            currentRoom = nextRoom;
            guiPrinter.printCurrentLocation(currentRoom);
        }


    }

    private boolean quit(Command command) {
        if (command.hasDirectionWord()) {
            guiPrinter.printInvalidQuitMessage();
            return false;
        } else {
            return true;
        }
    }
}
