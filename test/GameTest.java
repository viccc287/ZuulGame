package test;

import src.Game;
import org.junit.Test;


public class GameTest {

    @Test
    public void playTest(){
        Game game = new Game();
        game.play();
    }
}