package inaki.sw.mines.controller;

import inaki.sw.mines.model.Board;
import inaki.sw.mines.view.IChooseGameView;
import inaki.sw.mines.view.IMainView;
import inaki.sw.mines.view.ISelectNameView;
import inaki.sw.mines.view.IStatisticsView;
import java.awt.event.ActionEvent;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author inaki
 * @since 2.3.1
 */
public class ControllerIT {

    private static final int DELAY = 50;

    private static Controller c;

    public ControllerIT() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException, InterruptedException {
        c = new Controller();
        c.testingConfig();
        c.startController();
        Thread.sleep(DELAY);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of actionPerformed method, of class Controller. Tests playing and
     * replaying the different game types.
     *
     * @throws java.io.IOException
     * @throws java.lang.InterruptedException
     */
    @Test
    public void testPlayAndReplayDifferentGames() throws IOException, InterruptedException {
        Board board;
        // Select game EASY
        c.actionPerformed(new ActionEvent(c, 0, IChooseGameView.CGV_EASY));
        Thread.sleep(DELAY);
        board = c.getBoard();
        assertEquals(8, board.getHeight());
        assertEquals(8, board.getWidth());
        assertEquals(10, board.getMines());
        // Play and replay
        playAndReplay(board);

        // New game
        c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_NEW));
        Thread.sleep(DELAY);

        // Select game MEDIUM
        c.actionPerformed(new ActionEvent(c, 0, IChooseGameView.CGV_MEDIUM));
        Thread.sleep(DELAY);
        board = c.getBoard();
        assertEquals(16, board.getHeight());
        assertEquals(16, board.getWidth());
        assertEquals(40, board.getMines());
        // Play and replay
        playAndReplay(board);

        // New game
        c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_NEW));
        Thread.sleep(DELAY);

        // Select game HARD
        c.actionPerformed(new ActionEvent(c, 0, IChooseGameView.CGV_HARD));
        Thread.sleep(DELAY);
        board = c.getBoard();
        assertEquals(16, board.getHeight());
        assertEquals(30, board.getWidth());
        assertEquals(99, board.getMines());
        // Play and replay
        playAndReplay(board);
    }

    private void playAndReplay(Board board) throws InterruptedException {
        // Play
        playGame(board);
        Thread.sleep(DELAY);
        // Save game
        c.actionPerformed(new ActionEvent(c, 0, IStatisticsView.SV_SAVE));
        Thread.sleep(DELAY);
        c.actionPerformed(new ActionEvent(c, 0, ISelectNameView.SNV_OK));
        Thread.sleep(DELAY);
        // Close statistics view
        c.actionPerformed(new ActionEvent(c, 0, IStatisticsView.SV_OK));
        Thread.sleep(DELAY);
        // Restart game
        c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_RESTART));
        Thread.sleep(DELAY);
        // Solve
        c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_SOLVE));
        Thread.sleep(DELAY);
        // Save game
        c.actionPerformed(new ActionEvent(c, 0, IStatisticsView.SV_SAVE));
        Thread.sleep(DELAY);
        c.actionPerformed(new ActionEvent(c, 0, ISelectNameView.SNV_OK));
        Thread.sleep(DELAY);
        // Close statistics view
        c.actionPerformed(new ActionEvent(c, 0, IStatisticsView.SV_OK));
        Thread.sleep(DELAY);
    }

    private void playGame(Board board) {
        for (int i = 0; i < board.getHeight() * board.getWidth() - board.getMines(); i++) {
            c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_CLUE_WHITE_AREA));
        }
        for (int i = 0; i < board.getMines() * 8; i++) {
            c.actionPerformed(new ActionEvent(c, 0, IMainView.MV_CLUE_A_NUMBER));
        }
    }

}
