import exceptions.RoomFileKeyException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class RoomFileLoader {

    private static RoomFileLoader instance;

    private final RoomInstantiator instantiator = RoomInstantiator.getInstance();

    private RoomFileLoader(){

    }
    public static RoomFileLoader getInstance() {
        if (instance == null) instance = new RoomFileLoader();
        return instance;
    }

    private final List<Hashtable<String,String>> roomsToCreate = new ArrayList<>();


    public void loadRooms(){
        parseRooms();
        instantiator.createRoomInstances(roomsToCreate);
        instantiator.setExitsForRoomInstances(roomsToCreate);
    }

    private void parseRooms(){
        try {
            Object jsonParse = new JSONParser().parse(new FileReader("rooms.json"));
            JSONArray roomJsonArray = (JSONArray) jsonParse;

            for (int i = 0; i < roomJsonArray.size(); i++) {

                Hashtable<String, String> roomAttributes = new Hashtable<>();

                JSONObject roomJson = (JSONObject) roomJsonArray.get(i);
                String roomName = (String) roomJson.get("name");
                if (roomName == null)
                    throw new RoomFileKeyException("Error: Key \"name\" for some room does not exist in rooms.json file");

                String roomDescription = (String) roomJson.get("description");
                if (roomDescription == null)
                    throw new RoomFileKeyException("Error: Key \"description\" for room \"" + roomName + "\" does not exist in rooms.json file");

                String roomNorthExit = (String) roomJson.get("northExit");
                if (roomNorthExit == null)
                    throw new RoomFileKeyException("Error: Key \"northExit\" for room \"" + roomName + "\" does not exist in rooms.json file");

                String roomEastExit = (String) roomJson.get("eastExit");
                if (roomEastExit == null)
                    throw new RoomFileKeyException("Error: Key \"eastExit\" for room \"" + roomName + "\" does not exist in rooms.json file");

                String roomSouthExit = (String) roomJson.get("southExit");
                if (roomSouthExit == null)
                    throw new RoomFileKeyException("Error: Key \"southExit\" for room \"" + roomName + "\" does not exist in rooms.json file");

                String roomWestExit = (String) roomJson.get("westExit");
                if (roomWestExit == null)
                    throw new RoomFileKeyException("Error: Key \"westExit\" for room \"" + roomName + "\" does not exist in rooms.json file");

                roomAttributes.put("name",roomName);
                roomAttributes.put("description",roomDescription);
                roomAttributes.put("northExit",roomNorthExit);
                roomAttributes.put("eastExit",roomEastExit);
                roomAttributes.put("southExit",roomSouthExit);
                roomAttributes.put("westExit",roomWestExit);

                roomsToCreate.add(roomAttributes);
            }

        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }

    }


    public Room getRoomInstanceByName(String roomName){
        return instantiator.getRoomInstanceByName(roomName);
    }

}
