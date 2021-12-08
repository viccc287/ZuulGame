
public class Game {
    private Room currentRoom;
    private final RoomManager roomManager = RoomManager.getInstance();

    public Game() {
        roomManager.createRooms();
        currentRoom = roomManager.getRoomInstanceByName("outside");
    }

}
