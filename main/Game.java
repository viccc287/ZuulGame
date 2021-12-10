package main;

public class Game {
    public Room currentRoom;
    public GUIPrinter guiPrinter;
    public CommandParser commandParser;
    public CommandProcessor commandProcessor;
    private final RoomManager roomManager = RoomManager.getInstance();

    public Game() {
        guiPrinter = new GUIPrinter();
        commandParser = new CommandParser();
        roomManager.createRooms();
        currentRoom = roomManager.getStartingRoom();
        commandProcessor = new CommandProcessor(currentRoom,guiPrinter);
    }

    public void play(){
        guiPrinter.printWelcome(currentRoom);
        boolean quit = false;
        while (!quit){
            quit = commandProcessor.processCommand(commandParser);
        }
        guiPrinter.printGoodbye();
    }

}
