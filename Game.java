
public class Game {
    public Room currentRoom;
    private final RoomManager roomManager = RoomManager.getInstance();

    public Game() {
        roomManager.createRooms();
        currentRoom = roomManager.getStartingRoom();
    }

}
