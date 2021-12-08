
public class Game {
    private Room currentRoom;
    private final RoomFileLoader roomFileLoader = RoomFileLoader.getInstance();

    public Game() {
        roomFileLoader.loadRooms();
        currentRoom = roomFileLoader.getRoomInstanceByName("outside");
    }

}
