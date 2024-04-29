package inaki.sw.mines.model;

import java.awt.Color;
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

    public static final int BOARD_MINE = -1;
    public static final int BOARD_WARNING = -2;
    public static final int BOARD_ERROR = -3;
    public static final int BOARD_0 = 0;
    public static final int BOARD_1 = 1;
    public static final int BOARD_2 = 2;
    public static final int BOARD_3 = 3;
    public static final int BOARD_4 = 4;
    public static final int BOARD_5 = 5;
    public static final int BOARD_6 = 6;
    public static final int BOARD_7 = 7;
    public static final int BOARD_8 = 8;

    private static final Logger LOGGER = getLogger(Board.class.getName());
    private final int height;
    private final int width;
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
    public int getValue(final int h, final int w) {
        return this.board[h][w];
    }

    /**
     * Generates a random board.
     *
     * @param debug debug mode enabled or disabled
     */
    public void generate(final boolean debug) {
        if (height * width < mines) {
            LOGGER.warning("There are more mines than holes to place them");
            exit(-1);
        } else {
            placeMines();
            placeNumbers();
            if (debug) {
                printBoard();
            }
        }
    }

    /**
     * Prints the board trough the standard output.
     */
    public void printBoard() {
        for (int j = 0; j < width; j++) {
            out.print("\t["+j + "]");
        }
        out.println("");
        for (int i = 0; i < height; i++) {
            out.print("["+i + "]\t");
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
        int i = 0;
        while (i < mines) {
            final int _m = current().nextInt(0, height);
            final int _n = current().nextInt(0, width);
            if (board[_m][_n] != BOARD_MINE) {
                board[_m][_n] = BOARD_MINE;
                i++;
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

    public static Color colorByValue(final int number) {
        switch (number) {
            case BOARD_1:
                return Color.decode("#2222FF");
            case BOARD_2:
                return Color.decode("#008800");
            case BOARD_3:
                return Color.decode("#EE0000");
            case BOARD_4:
                return Color.decode("#000088");
            case BOARD_5:
                return Color.decode("#800000");
            case BOARD_6:
                return Color.decode("#0099CC");
            case BOARD_7:
                return Color.decode("#990099");
            case BOARD_8:
                return Color.decode("#CC9900");
            case BOARD_WARNING:
                return Color.decode("#FF6600");
            case BOARD_ERROR:
                return Color.decode("#FF0000");
            default:
                return Color.decode("#000000");
        }
    }
}
