
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.projects.Game;
import org.projects.exception.GameOverException;
import org.projects.exception.InvalidPlayerException;
import org.projects.models.Board;
import org.projects.models.Cell;
import org.projects.models.DefaultMove;
import org.projects.models.Player;
import org.projects.strategy.Dice;
import org.yaml.snakeyaml.Yaml;
import strategy.MockDice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameTest {
    private Game game;
    List<Player> playerList;
    Board board;
    Dice dice;

    @Before
    public void before() throws FileNotFoundException {
        InputStream input = new FileInputStream("/Users/sagrawal/IdeaProjects/snakes-and-ladders/src/test/resources/config.yaml");

        Yaml yaml = new Yaml();
        Map<String, Object> config = yaml.load(input);

        // Extract parameters
        int numPlayers = (int) config.get("players");
        int boardSize = (int) config.get("boardSize");
        int numSnakes = (int) config.get("snakes");
        int numLadders = (int) config.get("ladders");
        int numDice = (int) config.get("dices");
        String movementStrategy = (String) config.get("movementStrategy");

        playerList = new ArrayList<>();

        for(int i=0; i<numPlayers; i++) {
            playerList.add(new Player(0, String.valueOf('A'+i)));
        }

        List<Cell> cells = getCells(boardSize);
        board = new Board(cells);
        dice = new MockDice();
        game = new Game(playerList, board, dice);
    }

    @Test
    public void playTest() throws InvalidPlayerException {
        Player p = new Player(1, "Test");
        game.setDice(new MockDice());
        game.play(p);
        Assert.assertEquals(p.getPosition(), 26);
    }

    @Test
    public void endToEndTest() throws InvalidPlayerException, GameOverException {
        Player p1 = playerList.get(0);
        Player p2 = playerList.get(1);
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(25, p1.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(25, p2.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(50, p1.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(50, p2.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(75, p1.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(75, p2.getPosition());
        game.play(game.getNextPlayerToPlay());
        Assert.assertEquals(100, p1.getPosition());
        Assert.assertEquals(p1, game.getRes().get(1));
        p1.setPosition(0);
        p2.setPosition(0);
        game.getPlayerQueue().add(p1);
    }

    @Test(expected = GameOverException.class)
    public void testGameOver() throws InvalidPlayerException, GameOverException {
        Player p1 = game.getNextPlayerToPlay();
        p1.setPosition(75);
        game.play(p1);
        game.getNextPlayerToPlay();
    }

    @Test(expected = InvalidPlayerException.class)
    public void testInvalidPlayerPlay() throws InvalidPlayerException {
        Player player = new Player(1, "Test");
        player.setPosition(100);
        game.play(player);
    }

    public static List<Cell> getCells(int numberOfCells) {
        List<Cell> cells = new ArrayList<>();
        for (int i = 1; i <= numberOfCells; i++) {
            cells.add(new Cell(i, new DefaultMove()));
        }
        return cells;
    }
}
