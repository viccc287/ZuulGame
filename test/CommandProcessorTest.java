package test;

import main.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CommandProcessorTest {

    CommandProcessor commandProcessor;
    Command command;
    Room currentRoom;

    @Parameterized.Parameters
    public static Collection<Object> getData(){
        return Arrays.asList(new Object[][]{
                {new Command("go","west")},
                {new Command("go","east")},
                {new Command("go","south")},
                {new Command("go","north")},
        });
    }

    public CommandProcessorTest(Command command){
        this.command = command;
    }

    @Before
    public void before(){
        currentRoom = new Room("outside","outside the main entrance of the university");
        Room northExit = new Room("north","moved to north");
        Room eastExit = new Room("east","moved to east");
        Room southExit = new Room("south","moved to south");
        Room westExit = new Room("west","moved to west");

        currentRoom.setExits(northExit,eastExit,southExit,westExit);
        commandProcessor = new CommandProcessor(currentRoom, new GUIPrinter());
    }
    @Test
    public void goRoomTest() {
        boolean directionedToRoom = commandProcessor.goRoom(command);
        assertTrue(directionedToRoom);
    }


    @Test
    public void processCommandTest(){
        boolean quit = commandProcessor.processCommand(new CommandParser());
        assertTrue(quit);
    }
}