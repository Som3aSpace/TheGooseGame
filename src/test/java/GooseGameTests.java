import org.junit.Test;
import service.GooseGame;

import static org.junit.Assert.assertEquals;

public class GooseGameTests {
    GooseGame gooseGame = new GooseGame();

    /**
     * check add player method and duplicated values handling
     */
    @Test
    public void addPlayer(){
        assertEquals(gooseGame.inputProcessor("add player mo"),"players: mo");
        assertEquals(gooseGame.inputProcessor("add player fo"),"players: mo, fo");
        assertEquals(gooseGame.inputProcessor("add player mo"),"player exists"+System.lineSeparator()+"players: mo, fo");
    }

    /**
     * checking moving method
     */
    @Test
    public void movePlayer(){
        assertEquals(gooseGame.inputProcessor("add player mo"),"players: mo");
        assertEquals(gooseGame.inputProcessor("add player fo"),"players: mo, fo");
        assertEquals(gooseGame.inputProcessor("move mo 4,4"),"player rolls 4, 4 .mo move from 0 to 8");
        assertEquals(gooseGame.inputProcessor("move fo 1,3"),"player rolls 1, 3 .fo move from 0 to 4");
    }

    /**
     * checking step on goose
     */
    @Test
    public void movePlayerToGoose(){
        assertEquals(gooseGame.inputProcessor("add player fo"),"players: fo");
        assertEquals(gooseGame.inputProcessor("move fo 1,4"),"player rolls 1, 4 .fo move from 0 to 5, the Goose fo moves again to 10");
    }

    /**
     * check stepping on bridge
     */
    @Test
    public void stepOnBridge(){
        assertEquals(gooseGame.inputProcessor("add player mo"),"players: mo");
        assertEquals(gooseGame.inputProcessor("move mo 2,4"),"player rolls 2, 4 .mo move from 0 to 6 the bridge!! . mo jumps to 12");

    }

    /**
     * prank player to returns to the occupied attacker old position
     */
    @Test
    public void prankPlayer(){
        assertEquals(gooseGame.inputProcessor("add player mo"),"players: mo");
        assertEquals(gooseGame.inputProcessor("add player fo"),"players: mo, fo");
        assertEquals(gooseGame.inputProcessor("move mo 4,4"),"player rolls 4, 4 .mo move from 0 to 8");
        assertEquals(gooseGame.inputProcessor("move fo 4,4"),"player rolls 4, 4 .fo move from 0 to 8 on 8 there is player mo, who returns to 0");
    }


}
