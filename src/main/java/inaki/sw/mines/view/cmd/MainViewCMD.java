package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.Board;
import inaki.sw.mines.model.Clue;
import static inaki.sw.mines.model.Clue.FLAG;
import static inaki.sw.mines.model.Clue.NUMBER;
import static inaki.sw.mines.model.Clue.WHITE_AREA;
import inaki.sw.mines.view.IMainView;
import inaki.sw.mines.view.cmd.utils.Ansi;
import java.awt.Point;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_F;
import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_R;
import static java.awt.event.KeyEvent.VK_V;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Math.max;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.concurrent.ThreadLocalRandom.current;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class MainViewCMD implements IMainView {

    private enum BoardCellStatus {
        DISCOVERED, FLAG, FLAG_FAILED, HIDDEN
    }

    private enum GameStatus {
        NEW, LOST, PAUSED, PLAYING, WON
    }

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Controller c;
    private Board board;
    private BoardCellStatus[][] boardCells;
    private GameStatus gameStatus;
    private String time;
    private String version;
    private int flags;
    private float discoveredPercentage;
    private boolean clue1Enabled;
    private boolean clue2Enabled;
    private boolean clue3Enabled;
    private int primaryClikNumber;
    private int secondaryClikNumber;
    private List<String> flagExceededWarning;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void startView() {
        repaint();
        String s = readOptionMain();

        if (s == null) {
            startView();
            return;
        }
        s = s.toUpperCase();
        if ("SOLVE".equals(s)) {
            c.actionPerformed(new ActionEvent(this, 0, MV_SOLVE));
            repaint();
            s = readOptionMain();
            if (s == null) {
                startView();
                return;
            }
        }
        if ("".equals(s)) {
            if (gameStatus.equals(GameStatus.PLAYING)) {
                startView();
                return;
            }
            System.out.println("Bye!");
            System.exit(0);
        }

        switch (s.charAt(0)) {
            case VK_C:
                if (gameStatus.equals(GameStatus.NEW) || gameStatus.equals(GameStatus.PLAYING)) {
                    Clue clue = readOptionClue();
                    if (clue == null) {
                        startView();
                        break;
                    }
                    switch (clue) {
                        case FLAG:
                            c.actionPerformed(new ActionEvent(this, VK_C, MV_CLUE_A_FLAG));
                            break;
                        case NUMBER:
                            c.actionPerformed(new ActionEvent(this, VK_C, MV_CLUE_A_NUMBER));
                            break;
                        case WHITE_AREA:
                            c.actionPerformed(new ActionEvent(this, VK_C, MV_CLUE_WHITE_AREA));
                            break;
                        default:
                            break;
                    }
                }
                startView();
                break;
            case VK_F:
                if (gameStatus.equals(GameStatus.NEW) || gameStatus.equals(GameStatus.PLAYING)) {
                    System.out.print("Row: ");
                    final int mf = readOptionCoordinate(-1);
                    System.out.print("Column: ");
                    final int nf = readOptionCoordinate(-1);
                    secondaryClikNumber++;
                    mark(mf, nf);
                }
                startView();
                break;
            case VK_N:
                c.actionPerformed(new ActionEvent(this, VK_N, MV_NEW));
                startView();
                break;
            case VK_P:
                if (gameStatus.equals(GameStatus.PLAYING) || gameStatus.equals(GameStatus.PAUSED)) {
                    c.actionPerformed(new ActionEvent(this, VK_P, MV_CHRONO));
                }
                startView();
                break;
            case VK_R:
                c.actionPerformed(new ActionEvent(this, VK_R, MV_RESTART));
                startView();
                break;
            case VK_V:
                if (gameStatus.equals(GameStatus.NEW) || gameStatus.equals(GameStatus.PLAYING)) {
                    System.out.print("Row: ");
                    final int mv = readOptionCoordinate(-1);
                    System.out.print("Column: ");
                    final int nv = readOptionCoordinate(-1);
                    primaryClikNumber++;
                    uncover(mv, nv);
                }
                startView();
                break;
            default:
                startView();
                break;
        }
    }

    @Override
    public void hideView() {
        System.out.print(Ansi.CLS + Ansi.HOME);
    }

    @Override
    public int getHorizontal() {
        return board.getWidth();
    }

    @Override
    public int getVertical() {
        return board.getHeight();
    }

    @Override
    public int getMines() {
        return board.getMines();
    }

    @Override
    public int getPrimaryClikNumber() {
        return primaryClikNumber;
    }

    @Override
    public int getSecondaryClikNumber() {
        return secondaryClikNumber;
    }

    @Override
    public void setBoard(Board b) {
        this.board = b;
        initializeIndicators();
        initializeCells();
    }

    @Override
    public void solveBoard() {
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (boardCells[i][j].equals(BoardCellStatus.HIDDEN)) {
                    if (board.getValue(i, j) == Board.BOARD_MINE) {
                        mark(i, j);
                    } else {
                        uncover(i, j);
                    }
                }
            }
        }
        discoveredPercentage = 100;
        gameStatus = GameStatus.WON;
    }

    @Override
    public void updateChronometer(int m, int s) {
        time = (m < 10 ? "0" : "") + m + ":" + (s < 10 ? "0" : "") + s;
    }

    @Override
    public int getDiscoveredPrecentage() {
        return (int) discoveredPercentage;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        gameStatus = readOnly ? GameStatus.PAUSED : GameStatus.PLAYING;
    }

    @Override
    public void showClue(Clue clue) {
        switch (clue) {
            case WHITE_AREA:
                showClue1();
                break;
            case NUMBER:
                showClue2();
                break;
            case FLAG:
                showClue3();
                break;
            default:
                break;
        }
    }

    @Override
    public void setVersion(String version) {
        this.version = version != null ? version : "";
    }

    @Override
    public void disableNimbus(boolean disableNimbus) {
        // empty
    }

    private void showTitle() {
        System.out.print(Ansi.CLS + Ansi.HOME);
        String s = Ansi.FG_CYAN + Ansi.BOLD + "\n" + "+-";
        String title = TITLE.toUpperCase() + (version.equals("") ? "" : " v" + version) + " | " + time;
        for (int i = 0; i < title.length(); i++) {
            s += "-";
        }
        s += "-+\n";
        System.out.println(s + "| " + title + " |" + s + Ansi.RESET);
    }

    private void initializeIndicators() {
        time = "00:00";
        gameStatus = GameStatus.NEW;
        time = "00:00";
        flags = 0;
        discoveredPercentage = 0f;
        clue1Enabled = true;
        clue2Enabled = true;
        clue3Enabled = true;
        primaryClikNumber = 0;
        secondaryClikNumber = 0;
        flagExceededWarning = new ArrayList<>();
    }

    private void initializeCells() {
        boardCells = new BoardCellStatus[board.getHeight()][board.getWidth()];
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                boardCells[i][j] = BoardCellStatus.HIDDEN;
            }
        }
    }

    private void repaint() {
        showTitle();
        showBoard();
    }

    private void showBoard() {
        System.out.print("    " + (board.getHeight() > 9 ? " " : ""));
        for (int j = 0; j < board.getWidth(); j++) {
            System.out.print(j + " " + (board.getWidth() > 9 && j < 10 ? " " : ""));
        }
        System.out.print("\n  " + (board.getHeight() > 9 ? " " : "") + "+-");
        for (int j = 0; j < board.getWidth(); j++) {
            System.out.print((j > 9 ? "-" : "") + "--" + (board.getWidth() > 9 && j < 10 ? "-" : ""));
        }
        System.out.print("+");
        for (int i = 0; i < board.getHeight(); i++) {
            System.out.print("\n" + (board.getHeight() > 9 && i < 10 ? " " : "") + i + " |");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(" " + valueByNumber(boardCells[i][j], board.getValue(i, j)) + (board.getWidth() > 9 ? " " : ""));
            }
            System.out.print(" | " + i);
        }
        System.out.print("\n  " + (board.getHeight() > 9 ? " " : "") + "+-");
        for (int j = 0; j < board.getWidth(); j++) {
            System.out.print((j > 9 ? "-" : "") + "--" + (board.getWidth() > 9 && j < 10 ? "-" : ""));
        }
        System.out.print("+");
        System.out.print("\n    " + (board.getHeight() > 9 ? " " : ""));
        for (int j = 0; j < board.getWidth(); j++) {
            System.out.print(j + " " + (board.getWidth() > 9 && j < 10 ? " " : ""));
        }

        System.out.println("\n" + (flags > board.getMines() ? Ansi.FG_RED : "") + "Flags: " + flags + "/" + board.getMines() + Ansi.RESET + ", Discovered: " + (int) discoveredPercentage + "%");

        if (gameStatus.equals(GameStatus.LOST)) {
            System.out.println("\n" + Ansi.FG_RED + "You lost the game :(" + Ansi.RESET);
        } else if (gameStatus.equals(GameStatus.WON)) {
            System.out.println("\n" + Ansi.FG_GREEN + "You won game :)" + Ansi.RESET);
        }

        for (String warning : flagExceededWarning) {
            System.out.println(Ansi.BOLD + Ansi.FG_YELLOW + warning + Ansi.RESET);
        }
    }

    private String valueByNumber(BoardCellStatus status, int value) {
        switch (status) {
            case DISCOVERED:
                switch (value) {
                    case Board.BOARD_MINE:
                        return "*";
                    case Board.BOARD_0:
                        return " ";
                    case Board.BOARD_1:
                        return Ansi.BOLD + Ansi.FG_BLUE + value + Ansi.RESET;
                    case Board.BOARD_2:
                        return Ansi.FG_GREEN + value + Ansi.RESET;
                    case Board.BOARD_3:
                        return Ansi.FG_RED + value + Ansi.RESET;
                    case Board.BOARD_4:
                        return Ansi.FG_BLUE + value + Ansi.RESET;
                    case Board.BOARD_5:
                        return Ansi.BOLD + Ansi.FG_RED + value + Ansi.RESET;
                    case Board.BOARD_6:
                        return Ansi.FG_CYAN + value + Ansi.RESET;
                    case Board.BOARD_7:
                        return Ansi.FG_PURPLE + value + Ansi.RESET;
                    case Board.BOARD_8:
                        return Ansi.FG_YELLOW + value + Ansi.RESET;
                    default:
                        return value + "";
                }
            case FLAG:
                return "F";
            case FLAG_FAILED:
                return Ansi.BOLD + Ansi.FG_YELLOW + "X" + Ansi.RESET;
            case HIDDEN:
            default:
                return "?";
        }
    }

    private String readOptionMain() {
        switch (gameStatus) {
            case NEW:
                System.out.println("\nN=New, R=Restart, C=Clue, V=Reveal cell, F=Put flag, ENTER=Exit");
                break;
            case PLAYING:
                System.out.println("\nN=New, R=Restart, C=Clue, P=Pause, V=Reveal cell, F=Put flag");
                break;
            case PAUSED:
                System.out.println("\nN=New, R=Restart, P=Continue, ENTER=Exit");
                break;
            default:
                System.out.println("\nN=New, R=Restart, ENTER=Exit");
                break;
        }
        System.out.print("Type an option: ");

        try {
            return in.readLine();
        }
        catch (IOException ex) {
            Logger.getLogger(ChooseGameViewCMD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private Clue readOptionClue() {
        List<String> clues = new ArrayList<>();
        if (clue1Enabled) {
            clues.add("1=White area");
        }
        if (clue2Enabled) {
            clues.add("2=A number");
        }
        if (clue3Enabled) {
            clues.add("3=A flag");
        }
        if (clues.isEmpty()) {
            clues.add(Ansi.FG_YELLOW + "No clue is available" + Ansi.RESET);
        }
        clues.add("ENTER=Return");
        String clueMenu = Arrays.toString(clues.toArray());
        System.out.println(clueMenu.substring(1, clueMenu.length() - 1));
        System.out.print("Type an option: ");

        final String s;
        try {
            s = in.readLine();
            switch (s) {
                case "1":
                    return Clue.WHITE_AREA;
                case "2":
                    return Clue.NUMBER;
                case "3":
                    return Clue.FLAG;
                default:
                    return null;
            }
        }
        catch (IOException ex) {
            Logger.getLogger(ChooseGameViewCMD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    private int readOptionCoordinate(int def) {
        final String s;
        try {
            s = in.readLine();
            return s.isEmpty() ? def : Integer.parseInt(s);
        }
        catch (IOException | NumberFormatException ex) {
            return -1;
        }
    }

    private int countDiscoveredCells() {
        int discovered = 0;
        for (int i = 0; i < board.getHeight(); i++) {
            for (int j = 0; j < board.getWidth(); j++) {
                if (boardCells[i][j].equals(BoardCellStatus.DISCOVERED)) {
                    discovered++;
                }
            }
        }
        return discovered;
    }

    private void uncover(int _m, int _n) {
        if (_m >= 0 && _n >= 0 && _m < board.getHeight() && _n < board.getWidth() && boardCells[_m][_n].equals(BoardCellStatus.HIDDEN)) {
            boardCells[_m][_n] = BoardCellStatus.DISCOVERED;
            int discovered = countDiscoveredCells();
            if (discovered == 1) {
                gameStatus = GameStatus.PLAYING;
                c.actionPerformed(new ActionEvent(this, 0, MV_START_CHRONO));
            }
            if (discovered == board.getHeight() * board.getWidth() - board.getMines()) {
                gameStatus = GameStatus.WON;
                c.actionPerformed(new ActionEvent(this, 0, MV_WIN));
            }
            discoveredPercentage = discovered / (float) (board.getHeight() * board.getWidth() - board.getMines()) * 100f;
            switch (board.getValue(_m, _n)) {
                case Board.BOARD_MINE:
                    for (int i = 0; i < board.getHeight(); i++) {
                        for (int j = 0; j < board.getWidth(); j++) {
                            if (board.getValue(i, j) != Board.BOARD_MINE && boardCells[i][j].equals(BoardCellStatus.FLAG)) {
                                boardCells[i][j] = BoardCellStatus.FLAG_FAILED;
                            } else {
                                boardCells[i][j] = BoardCellStatus.DISCOVERED;
                            }
                        }
                    }
                    gameStatus = GameStatus.LOST;
                    c.actionPerformed(new ActionEvent(this, 0, MV_LOST));
                    break;
                case Board.BOARD_0:
                    final int m1 = max(0, _m - 1);
                    final int m2 = min(board.getHeight() - 1, _m + 1);
                    final int n1 = max(0, _n - 1);
                    final int n2 = min(board.getWidth() - 1, _n + 1);
                    for (int _i = m1; _i <= m2; _i++) {
                        for (int _j = n1; _j <= n2; _j++) {
                            if (boardCells[_i][_j].equals(BoardCellStatus.HIDDEN)) {
                                uncover(_i, _j);
                            }
                        }
                    }
                    break;
                case Board.BOARD_1:
                case Board.BOARD_2:
                case Board.BOARD_3:
                case Board.BOARD_4:
                case Board.BOARD_5:
                case Board.BOARD_6:
                case Board.BOARD_7:
                case Board.BOARD_8:
                    // empty
                    break;
                default:
                    break;
            }
        } else if (_m >= 0 && _n >= 0 && _m < board.getHeight() && _n < board.getWidth() && boardCells[_m][_n].equals(BoardCellStatus.DISCOVERED)) {
            uncoverSurrounding(_m, _n);
        }
    }

    private void mark(int _m, int _n) {
        if (_m >= 0 && _n >= 0 && _m < board.getHeight() && _n < board.getWidth() && !boardCells[_m][_n].equals(BoardCellStatus.DISCOVERED)) {
            /* Comprobamos si esta etiquetada o no */
            markUnmark(_m, _n);
            /* Comprobamos si hemos puesto demasiadas banderas al rededor de un numero */
            checkSurroundingFlagNumber(_m, _n);
        }
    }

    private void markUnmark(final int _m, final int _n) {
        if (boardCells[_m][_n].equals(BoardCellStatus.FLAG)) {
            // Si lo esta, la desetiquetamos
            boardCells[_m][_n] = BoardCellStatus.HIDDEN;
            this.flags--;
        } else {
            // Si no, la etiquetamos
            boardCells[_m][_n] = BoardCellStatus.FLAG;
            this.flags++;
        }
    }

    private void checkSurroundingFlagNumber(final int _m, final int _n) {
        final int m1 = max(0, _m - 1);
        final int m2 = min(board.getHeight() - 1, _m + 1);
        final int n1 = max(0, _n - 1);
        final int n2 = min(board.getWidth() - 1, _n + 1);
        flagExceededWarning = new ArrayList<>();
        for (int _i = m1; _i <= m2; _i++) {
            for (int _j = n1; _j <= n2; _j++) {
                if (boardCells[_i][_j].equals(BoardCellStatus.DISCOVERED) && surroundingFlagNumberExceeded(_i, _j)) {
                    flagExceededWarning.add("Number in row=" + _i + ", col=" + _j + " has too many flags surrounding");
                }
            }
        }
    }

    private boolean surroundingFlagNumberExceeded(final int _m, final int _n) {
        final int m1 = max(0, _m - 1);
        final int m2 = min(board.getHeight() - 1, _m + 1);
        final int n1 = max(0, _n - 1);
        final int n2 = min(board.getWidth() - 1, _n + 1);
        int flagNumber = 0;
        for (int _i = m1; _i <= m2; _i++) {
            for (int _j = n1; _j <= n2; _j++) {
                if ((_i != _m || _j != _n) && boardCells[_i][_j].equals(BoardCellStatus.FLAG)) {
                    flagNumber++;
                }
            }
        }
        return flagNumber > board.getValue(_m, _n);
    }

    private void uncoverSurrounding(final int _m, final int _n) {
        if (boardCells[_m][_n].equals(BoardCellStatus.DISCOVERED)) {
            final int number = board.getValue(_m, _n);
            if (number > Board.BOARD_0) {
                final int m1 = max(0, _m - 1);
                final int m2 = min(board.getHeight() - 1, _m + 1);
                final int n1 = max(0, _n - 1);
                final int n2 = min(board.getWidth() - 1, _n + 1);
                /* Check how many cells are marked surround it */
                int marked = 0;
                for (int _i = m1; _i <= m2; _i++) {
                    for (int _j = n1; _j <= n2; _j++) {
                        if (boardCells[_i][_j].equals(BoardCellStatus.FLAG)) {
                            marked++;
                        }
                    }
                }
                /* If are marked the same number as current cell's number,
                * we uncover the surrounding */
                if (number == marked) {
                    for (int _i = m1; _i <= m2; _i++) {
                        for (int _j = n1; _j <= n2; _j++) {
                            if (boardCells[_i][_j].equals(BoardCellStatus.HIDDEN)) {
                                uncover(_i, _j);
                            }
                        }
                    }
                }
            }
        }
    }

    private void showClue1() {
        if (clue1Enabled) {
            final Map<Integer, Point> hm = new HashMap<>();
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (boardCells[i][j].equals(BoardCellStatus.HIDDEN) && board.getValue(i, j) == Board.BOARD_0) {
                        hm.put(hm.size(), new Point(i, j));
                    }
                }
            }
            if (!hm.isEmpty()) {
                Point p = hm.get(current().nextInt(0, hm.size()));
                uncover(p.x, p.y);
            }
            clue1Enabled = false;
        }
    }

    private void showClue2() {
        if (clue2Enabled) {
            final Map<Integer, Point> hm = new HashMap<>();
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (boardCells[i][j].equals(BoardCellStatus.HIDDEN) && board.getValue(i, j) > Board.BOARD_0) {
                        hm.put(hm.size(), new Point(i, j));
                    }
                }
            }
            if (!hm.isEmpty()) {
                Point p = hm.get(current().nextInt(0, hm.size()));
                uncover(p.x, p.y);
            }
            clue2Enabled = false;
        }
    }

    private void showClue3() {
        if (clue3Enabled) {
            final Map<Integer, Point> hm = new HashMap<>();
            for (int i = 0; i < board.getHeight(); i++) {
                for (int j = 0; j < board.getWidth(); j++) {
                    if (boardCells[i][j].equals(BoardCellStatus.HIDDEN) && board.getValue(i, j) == Board.BOARD_MINE) {
                        hm.put(hm.size(), new Point(i, j));
                    }
                }
            }
            if (!hm.isEmpty()) {
                Point p = hm.get(current().nextInt(0, hm.size()));
                mark(p.x, p.y);
            }
            clue3Enabled = false;
        }
    }

}
