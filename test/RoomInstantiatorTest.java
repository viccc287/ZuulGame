package test;

import exceptions.RoomFileKeyException;
import exceptions.RoomInstanceNotFoundException;
import exceptions.RoomNotFoundException;
import main.Room;
import main.RoomInstantiator;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.Assert.*;

public class RoomInstantiatorTest {

    private static List<Hashtable<String, String>> roomsToCreate;
    private static RoomInstantiator roomInstantiator;

    @BeforeClass
    public static void insertInstance(){
        roomsToCreate = new ArrayList<>();
        roomInstantiator = RoomInstantiator.getInstance();

        Hashtable<String, String> roomHashTable1 = new Hashtable<>();
        roomHashTable1.put("name","bathroom");
        roomHashTable1.put("description","in the bathroom");
        Hashtable<String, String> roomHashTable2 = new Hashtable<>();
        roomHashTable2.put("name","gym");
        roomHashTable2.put("description","in the gym");

        roomsToCreate.add(roomHashTable1);
        roomsToCreate.add(roomHashTable2);

        roomInstantiator.createRoomInstances(roomsToCreate);

    }


    @Test(expected = RoomFileKeyException.class)
    public void createRoomInstancesEmptyNameTest() {
        Hashtable<String, String> roomHashTableNoName = new Hashtable<>();
        roomHashTableNoName.put("name","");
        roomHashTableNoName.put("description","no name");
        roomsToCreate.add(roomHashTableNoName);

        roomInstantiator.createRoomInstances(roomsToCreate);
    }

    @Test(expected = RoomNotFoundException.class)
    public void setExitsForRoomInstancesNullExitsTest() {
        roomInstantiator.setExitsForRoomInstances(roomsToCreate);
    }


    @Test(expected = RoomInstanceNotFoundException.class)
    public void getRoomInstanceByNameFailTest() {
        Room shop = roomInstantiator.getRoomInstanceByName("shop");
    }

    @Test
    public void getRoomInstanceByNamePassTest() {
        Room bathroom = roomInstantiator.getRoomInstanceByName("bathroom");
        assertNotNull(bathroom);
    }
}