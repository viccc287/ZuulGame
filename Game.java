
public class Game {
    public Room currentRoom;
    public GUIPrinter guiPrinter;
    public Parser parser;
    public CommandProcessor commandProcessor;
    private final RoomManager roomManager = RoomManager.getInstance();

    public Game() {
        guiPrinter = new GUIPrinter();
        parser = new Parser();
        roomManager.createRooms();
        currentRoom = roomManager.getStartingRoom();

        commandProcessor = new CommandProcessor(currentRoom,guiPrinter);
    }

    public void play(){
        guiPrinter.printWelcome(currentRoom);

        boolean quit = false;
        while (!quit){
            quit = commandProcessor.processCommand(parser);
        }

        guiPrinter.printGoodbye();
    }

}
