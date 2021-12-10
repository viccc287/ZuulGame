package test;

import main.Game;
import org.junit.Test;


public class GameTest {

    @Test
    public void playTest(){
        Game game = new Game();
        game.play();
    }
}