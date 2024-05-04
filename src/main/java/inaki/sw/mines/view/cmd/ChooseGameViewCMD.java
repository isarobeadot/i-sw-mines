package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.view.IChooseGameView;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class ChooseGameViewCMD implements IChooseGameView {

    private final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Controller c;
    private boolean statisticsEnabled;
    private int horizontal;
    private int vertical;
    private int mines;
    private String version;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void startView() {
        showTitle();

        System.out.println("1.-\t" + ANSI_FG_GREEN + "8 x 8 (10 mines)" + ANSI_RESET);
        System.out.println("2.-\t" + ANSI_FG_BLUE + "16 x 16 (40 mines)" + ANSI_RESET);
        System.out.println("3.-\t" + ANSI_FG_RED + "30 x 16 (99 mines)" + ANSI_RESET);
        System.out.println("4.-\t" + "Custom" + ANSI_RESET);
        if (statisticsEnabled) {
            System.out.println("5.-\t" + "Statistics" + ANSI_RESET);
        }
        System.out.println("ENTER.-\t" + "Exit" + ANSI_RESET);
        System.out.print("\nType an option: ");

        switch (readOption(0)) {
            case 0:
                System.out.println("Bye!");
                System.exit(0);
                break;
            case 1:
                c.actionPerformed(new ActionEvent(this, 1, CGV_EASY));
                break;
            case 2:
                c.actionPerformed(new ActionEvent(this, 1, CGV_MEDIUM));
                break;
            case 3:
                c.actionPerformed(new ActionEvent(this, 1, CGV_HARD));
                break;
            case 4:
                System.out.print("Horizontal (default 10): ");
                horizontal = betweenRange(readOption(10), 4, 30);
                System.out.print("Vertical (default 10): ");
                vertical = betweenRange(readOption(10), 4, 20);
                System.out.print("Mines (default 10): ");
                mines = betweenRange(readOption(10), 1, horizontal * vertical - 10);
                c.actionPerformed(new ActionEvent(this, 1, CGV_START));
                break;
            case 5:
                if (statisticsEnabled) {
                    c.actionPerformed(new ActionEvent(this, 1, CGV_STATISTICS));
                } else {
                    badOperation();
                }
                startView();
                break;
            default:
                badOperation();
                startView();
                break;
        }
    }

    @Override
    public void hideView() {
        System.out.print(ANSI_CLS + ANSI_HOME);
    }

    @Override
    public void enableStatistics(boolean b) {
        this.statisticsEnabled = b;
    }

    @Override
    public int getHorizontal() {
        return horizontal;
    }

    @Override
    public int getVertical() {
        return vertical;
    }

    @Override
    public int getMines() {
        return mines;
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
        System.out.print(ANSI_CLS + ANSI_HOME);
        String s = ANSI_FG_CYAN + ANSI_BOLD + "\n" + "+-";
        String title = TITLE.toUpperCase() + (version.equals("") ? "" : " v" + version);
        for (int i = 0; i < title.length(); i++) {
            s += "-";
        }
        s += "-+\n";
        System.out.println(s + "| " + title + " |" + s + ANSI_RESET);
    }

    private int readOption(int def) {
        final String s;
        try {
            s = in.readLine();
            return s.isEmpty() ? def : Integer.parseInt(s);
        }
        catch (IOException | NumberFormatException ex) {
            return -1;
        }
    }

    private void badOperation() {
        showTitle();

        System.err.println(ANSI_FG_RED + "Wrong Option" + ANSI_RESET);
        System.out.println("\nPress ENTER to continue...");
        try {
            in.readLine();
        }
        catch (IOException ex) {
            Logger.getLogger(ChooseGameViewCMD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int betweenRange(int number, int min, int max) {
        if (number < min) {
            number = min;
        } else if (number > max) {
            number = max;
        }
        return number;
    }

}
