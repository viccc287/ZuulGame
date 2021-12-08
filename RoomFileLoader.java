import exceptions.RoomFileKeyException;
import exceptions.RoomNotFoundException;
import exceptions.RoomNullException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

@SuppressWarnings("ForLoopReplaceableByForEach")
public class RoomFileLoader {

    private final List<Hashtable<String,String>> roomsToCreate = new ArrayList<>();
    private final List<String> roomsToCreateNames = new ArrayList<>();
    private final Hashtable<String,Room> roomInstances = new Hashtable<>();


    public void loadRooms(){
        parseRooms();
        createRoomInstances();
        setExitsForRoomInstances();
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

    private void createRoomInstances(){

        for (Hashtable<String,String> roomToCreate : roomsToCreate){
            String roomToCreateName = roomToCreate.get("name");
            String roomToCreateDescription = roomToCreate.get("description");
            try{

                if (roomToCreateName.equals("")) throw new RoomNullException("Room name key in file can not be empty");
                roomsToCreateNames.add(roomToCreateName);

                    Room roomInstance = Room.class.getDeclaredConstructor(String.class,String.class).newInstance(roomToCreateName,roomToCreateDescription);

                    roomInstances.put(roomToCreateName,roomInstance);
                }
                catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | RoomNullException e) {
                    e.printStackTrace();
                } {

                }


        }

    }

    private void setExitsForRoomInstances(){

        roomsToCreateNames.add(""); //Para que sea válido dejar las salidas vacías.

        for (Hashtable<String,String> roomToCreate : roomsToCreate){

            String roomToCreateName = roomToCreate.get("name");

            List<String> roomToCreateExits = new ArrayList<>();

            String roomToCreateNorthExit = roomToCreate.get("northExit");
            roomToCreateExits.add(roomToCreateNorthExit);

            String roomToCreateEastExit = roomToCreate.get("eastExit");
            roomToCreateExits.add(roomToCreateEastExit);

            String roomToCreateSouthExit = roomToCreate.get("southExit");
            roomToCreateExits.add(roomToCreateSouthExit);

            String roomToCreateWestExit = roomToCreate.get("westExit");


            roomToCreateExits.add(roomToCreateWestExit);


            if (roomsToCreateNames.containsAll(roomToCreateExits)){
                Room roomInstanceToSet = roomInstances.get(roomToCreateName);
                Room roomNorthExitToSet = roomInstances.get(roomToCreateNorthExit);
                Room roomEastExitToSet = roomInstances.get(roomToCreateEastExit);
                Room roomSouthExitToSet = roomInstances.get(roomToCreateSouthExit);
                Room roomWestExitToSet = roomInstances.get(roomToCreateWestExit);

                roomInstanceToSet.setExits(roomNorthExitToSet,roomEastExitToSet,roomSouthExitToSet,roomWestExitToSet);
            }
            else throw new RoomNotFoundException("Error: exit \""+roomToCreateName+"\" does not match any room name provided in rooms.json file");
        }

    }

    public Hashtable<String, Room> getRoomInstances() {
        return roomInstances;
    }
}
