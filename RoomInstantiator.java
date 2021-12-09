import exceptions.RoomFileKeyException;
import exceptions.RoomInstanceNotFoundException;
import exceptions.RoomNotFoundException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RoomInstantiator {

    private static RoomInstantiator instance;

    private final List<String> roomsToCreateNames = new ArrayList<>();
    private final Hashtable<String,Room> roomInstances = new Hashtable<>();

    private RoomInstantiator() {

    }

    public static RoomInstantiator getInstance() {
        if (instance == null) instance = new RoomInstantiator();
        return instance;
    }


    public void createRoomInstances(List<Hashtable<String,String>> roomsToCreate){

        for (Hashtable<String,String> roomToCreate : roomsToCreate){
            String roomToCreateName = roomToCreate.get("name");
            String roomToCreateDescription = roomToCreate.get("description");
            try{

                if (roomToCreateName.equals("")) throw new RoomFileKeyException("Room name key in file can not be empty");
                roomsToCreateNames.add(roomToCreateName);

                Room roomInstance = Room.class.getDeclaredConstructor(String.class,String.class).newInstance(roomToCreateName,roomToCreateDescription);

                roomInstances.put(roomToCreateName,roomInstance);
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException | RoomFileKeyException e) {
                e.printStackTrace();
            } {

            }


        }

    }

    public void setExitsForRoomInstances(List<Hashtable<String,String>> roomsToCreate){

        roomsToCreateNames.add(""); //Para que al verificar si se encuentra el nombre, se permita el vacío

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

            //Deben existir en el arreglo de nombres todos los nombres de las salidas a setear
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

    public Room getRoomInstanceByName(String roomName){
        Room instance = roomInstances.get(roomName);
        if (instance == null) throw new RoomInstanceNotFoundException("Room instance named \""+roomName+"\" can not be found");
        else return instance;
    }

}
