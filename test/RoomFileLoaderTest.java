package test;

import exceptions.RoomFileKeyException;
import exceptions.RoomsFileNotFoundException;
import src.RoomFileLoader;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

public class RoomFileLoaderTest {

    RoomFileLoader loader;

    @Before
    public void before(){
        this.loader = RoomFileLoader.getInstance();
    }

    @Test
    public void parseRoomsOutputTest() {
        List<Hashtable<String,String>> expectedList = new ArrayList<>();
        Hashtable<String, String> room1= new Hashtable<>();
        Hashtable<String, String> room2= new Hashtable<>();
        room1.put("name", "outside");
        room1.put("description", "outside the main entrance of the university");
        room1.put("northExit","theatre");
        room1.put("eastExit","lab");
        room1.put("southExit","pub");
        room1.put("westExit","");

        room2.put("name", "theatre");
        room2.put("description", "in a lecture theatre");
        room2.put("northExit","");
        room2.put("eastExit","");
        room2.put("southExit","");
        room2.put("westExit","outside");

        expectedList.add(room1);
        expectedList.add(room2);

        assertEquals(expectedList,loader.parseRooms("test/outputTestFile.json"));

    }

    @Test(expected = RoomFileKeyException.class)
    public void parseRoomsKeyExceptionTest(){
        loader.parseRooms("test/keyExceptionTestFile.json");
    }

    @Test(expected = RoomsFileNotFoundException.class)
    public void parseRoomsInvalidFileTest(){
        String invalidFilename = "test/invalid.json";
        loader.parseRooms(invalidFilename);
    }
}