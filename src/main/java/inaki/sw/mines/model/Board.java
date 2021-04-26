package inaki.sw.mines.model;

import java.awt.Dimension;
import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.lang.System.exit;
import static java.lang.System.out;
import static java.util.concurrent.ThreadLocalRandom.current;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class Board {

    public static final int MINE = -1;
    private static final Logger LOGGER = getLogger(Board.class.getName());
    private final int height, width;
    private final int mines;
    private final int[][] board;

    /**
     * Initializes the parametres needed to generate the board
     *
     * @param height the height of the board
     * @param width the width of the board
     * @param mines the number of mines of the board
     */
    public Board(final int width, final int height, final int mines) {
        this.height = height;
        this.width = width;
        this.mines = mines;
        this.board = new int[height][width];
    }

    /**
     * Initializes the parametres needed to generate the board
     *
     * @param d the dimension of the board
     * @param mines the number of mines of the board
     */
    public Board(final Dimension d, final int mines) {
        this.height = d.height;
        this.width = d.width;
        this.mines = mines;
        this.board = new int[height][width];
    }

    /**
     * Get the height of the board
     *
     * @return the height of the board
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the width of the board
     *
     * @return the width of the board
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the number of mines of the board
     *
     * @return the number of mines of the board
     */
    public int getMines() {
        return mines;
    }

    /**
     * Get the board
     *
     * @return the board
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * Get the value of the board at specified index
     *
     * @param h the height index of the board
     * @param w the width index of the board
     * @return the value of the board at specified index
     */
    public int getBoard(final int h, final int w) {
        return this.board[h][w];
    }

    /**
     * Generates a random board.
     */
    public void generate() {
        if (height * width < mines) {
            LOGGER.warning("There are more mines than holes to place them");
            exit(-1);
        } else {
            placeMines();
            placeNumbers();
        }
    }

    /**
     * Prints the board trough the standard output.
     */
    public void printBoard() {
        for (int i = 0; i < height; i++) {
            out.print(i + ":\t");
            for (int j = 0; j < width; j++) {
                out.print(board[i][j] + "\t");
            }
            out.println("");
        }
        out.println("");
    }

    /**
     * Places mines randomly through the board.
     */
    private void placeMines() {
        for (int i = 0; i < mines; i++) {
            final int _m = current().nextInt(0, height);
            final int _n = current().nextInt(0, width);
            if (board[_m][_n] == MINE) {
                i--;
            } else {
                board[_m][_n] = MINE;
            }
        }
    }

    /**
     * Fills the empty cells with numbers indicating the mine number around each
     * cell.
     */
    private void placeNumbers() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (board[i][j] != -1) {
                    final int m1 = max(0, i - 1);
                    final int m2 = min(height - 1, i + 1);
                    final int n1 = max(0, j - 1);
                    final int n2 = min(width - 1, j + 1);
                    int bombs = 0;
                    for (int _i = m1; _i <= m2; _i++) {
                        for (int _j = n1; _j <= n2; _j++) {
                            bombs += (board[_i][_j] == -1) ? (1) : (0);
                        }
                    }
                    board[i][j] = bombs;
                }
            }
        }
    }
}
