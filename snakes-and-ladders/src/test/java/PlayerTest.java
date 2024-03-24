
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.projects.Game;
import org.projects.exception.InvalidPlayerException;
import org.projects.models.Board;
import org.projects.models.Cell;
import org.projects.models.Player;
import org.projects.strategy.Dice;
import org.projects.strategy.NormalDice;
import strategy.MockDice;

import java.util.ArrayList;
import java.util.List;

import static models.BoardTest.getCells;


public class PlayerTest {

    private Game game;
    List<Player> playerList;
    Board board;
    Dice dice;

    @Before
    public void before() {
        playerList = new ArrayList<>();
        playerList.add(new Player(1, "A"));
        playerList.add(new Player(1, "B"));
        List<Cell> cells = getCells(100);
        board = new Board(cells);
        dice = new NormalDice(1, "MIN");
        game = new Game(playerList, board, dice);
    }

    @Test
    public void playerPositionTest() throws InvalidPlayerException {
        Player p = new Player(1, "Test");
        game.setDice(new MockDice());
        game.play(p);
        Assert.assertEquals(p.getPosition(), 26);
    }
}
