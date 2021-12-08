import java.util.Hashtable;
import java.util.List;

public class RoomManager {
    private final RoomFileLoader fileLoader = RoomFileLoader.getInstance();
    private final RoomInstantiator instantiator = RoomInstantiator.getInstance();

    private static RoomManager instance;

    private RoomManager(){

    }

    public static RoomManager getInstance(){
        if (instance == null) instance = new RoomManager();
        return instance;
    }

    public void createRooms() {
        List<Hashtable<String,String>> roomsToCreate = fileLoader.parseRooms();
        instantiator.createRoomInstances(roomsToCreate);
        instantiator.setExitsForRoomInstances(roomsToCreate);

    }

    public Room getRoomInstanceByName(String roomName){
        return instantiator.getRoomInstanceByName(roomName);
    }
}
