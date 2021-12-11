package test;

import src.CommandWords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class CommandWordsTest {

    CommandWords commandWords;
    String[] testCommands;

    @Parameterized.Parameters
    public static Collection<Object> getData(){
        return  Arrays.asList(new Object[][]{
                {new String[]{"go","quit","help","go"}},//Ejemplo valido
                {new String[]{"go","quit","help","run"}},//Ejemplo fallo
        });
    }

    public CommandWordsTest(String[] testCommands){
        this.testCommands = testCommands;
    }

    @Before
    public void before(){
        commandWords = CommandWords.getInstance();
    }
    @Test
    public void isCommandTest() {
        List<String> expected = new ArrayList<>();
        List<String> invalidCommands = new ArrayList<>();
        for (String commandString : testCommands) {
            if (!commandWords.isCommand(commandString)) {
                invalidCommands.add(commandString);
            }
        }
        assertEquals(expected,invalidCommands);
    }
}