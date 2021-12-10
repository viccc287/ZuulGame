package main;

import exceptions.RoomFileKeyException;
import exceptions.RoomsFileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class RoomFileLoader {

    private static RoomFileLoader instance;

    private String startingRoomName;

    private RoomFileLoader(){

    }
    public static RoomFileLoader getInstance() {
        if (instance == null) instance = new RoomFileLoader();
        return instance;
    }

    public List<Hashtable<String,String>> parseRooms(String filename){

        List<Hashtable<String,String>> roomsToCreate = new ArrayList<>();
        try {
            Object jsonParse = new JSONParser().parse(new FileReader(filename));
            JSONArray roomJsonArray = (JSONArray) jsonParse;

            for (int i = 0; i < roomJsonArray.size(); i++) {

                //Cada objeto JSON del archivo se parsea a una Hashtable donde la key es su respectivo atributo
                Hashtable<String, String> roomAttributes = new Hashtable<>();

                JSONObject roomJson = (JSONObject) roomJsonArray.get(i);

                if (i == 0){ //Para el primer objeto JSON, que debe contener la Room de inicio
                    String startingRoomName = (String) roomJson.get("startingRoom");
                    if (startingRoomName == null)
                        throw new RoomFileKeyException("Error: Key \"startingRoom\" does not exist in rooms.json file");
                    else this.startingRoomName = startingRoomName;

                }
                else {
                    String roomName = (String) roomJson.get("name");
                    if (roomName == null)
                        throw new RoomFileKeyException("Error: Key \"name\" for some room does not exist in rooms.json file");

                    //roomJson.get(key) devolverá nulo si no encuentra la key en el JSON, así que se va verificando
                    String roomDescription = (String) roomJson.get("description");
                    checkStringIsNotNull(roomDescription,"description",roomName);

                    String roomNorthExit = (String) roomJson.get("northExit");
                    checkStringIsNotNull(roomNorthExit,"northExit",roomName);

                    String roomEastExit = (String) roomJson.get("eastExit");
                    checkStringIsNotNull(roomEastExit,"eastExit",roomName);

                    String roomSouthExit = (String) roomJson.get("southExit");
                    checkStringIsNotNull(roomSouthExit,"southExit",roomName);

                    String roomWestExit = (String) roomJson.get("westExit");
                    checkStringIsNotNull(roomWestExit,"westExit",roomName);

                    roomAttributes.put("name", roomName);
                    roomAttributes.put("description", roomDescription);
                    roomAttributes.put("northExit", roomNorthExit);
                    roomAttributes.put("eastExit", roomEastExit);
                    roomAttributes.put("southExit", roomSouthExit);
                    roomAttributes.put("westExit", roomWestExit);

                    roomsToCreate.add(roomAttributes);
                }
            }

        }
        catch (FileNotFoundException e){
            throw new RoomsFileNotFoundException("Error: Room config file not found, please check file or directory names");
        }
        catch (IOException | ParseException e){
            e.printStackTrace();
        }
        return roomsToCreate;
    }

    public String getStartingRoomName(){
        return startingRoomName;
    }

    private void checkStringIsNotNull(String string, String keyThatDoesntExists, String roomName){
        if (string == null) throw new RoomFileKeyException("Error: Key \""+keyThatDoesntExists+"\" for room \"" + roomName + "\" does not exist in rooms.json file");
    }
    //Método auxiliar para evitar repetición de código en parseRooms()

}
