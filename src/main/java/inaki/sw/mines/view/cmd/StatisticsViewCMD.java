package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.GameType;
import static inaki.sw.mines.model.GameType.EASY;
import static inaki.sw.mines.model.GameType.HARD;
import static inaki.sw.mines.model.GameType.MEDIUM;
import inaki.sw.mines.view.IStatisticsView;
import inaki.sw.mines.view.cmd.utils.Ansi;
import inaki.sw.mines.view.cmd.utils.table.Cell;
import inaki.sw.mines.view.cmd.utils.table.Row;
import inaki.sw.mines.view.cmd.utils.table.Table;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.VK_S;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class StatisticsViewCMD implements IStatisticsView {

    private Controller c;
    private String version;
    private boolean readOnly;
    private String time;
    private int primaryClikNumber;
    private double ratio;
    private GameType gameType;
    private List<Integer> discoveredHistory;

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void startView() {
        showTitle();
        showStatistics();
        String s = readOption();

        if (s == null) {
            startView();
            return;
        }
        s = s.toUpperCase();
        if ("".equals(s)) {
            c.actionPerformed(new ActionEvent(this, VK_S, SV_OK));
        } else if (s.charAt(0) == VK_S) {
            c.actionPerformed(new ActionEvent(this, VK_S, SV_SAVE));
            startView();
        }
    }

    @Override
    public void hideView() {
        System.out.print(Ansi.CLS + Ansi.HOME);
    }

    @Override
    public void setTime(int m, int s) {
        time = (m < 10 ? "0" : "") + m + ":" + (s < 10 ? "0" : "") + s;
    }

    @Override
    public void setPrimaryClikNumber(int clikNo) {
        primaryClikNumber = clikNo;
    }

    @Override
    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    @Override
    public void setDiscoveredHistory(List<Integer> discoveredHistory) {
        this.discoveredHistory = discoveredHistory;
    }

    /**
     *
     * @param type
     */
    @Override
    public void setGameType(GameType type) {
        gameType = type;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
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
        String title = TITLE.toUpperCase() + (version.equals("") ? "" : " v" + version);
        for (int i = 0; i < title.length(); i++) {
            s += "-";
        }
        s += "-+\n";
        System.out.println(s + "| " + title + " |" + s + Ansi.RESET);
    }

    private void showStatistics() {
        if (!readOnly) {
            System.out.println(Ansi.FG_GREEN + "You won game :)" + Ansi.RESET + "\n");
        }

        String typeColor;
        switch (gameType) {
            case EASY:
                typeColor = Ansi.FG_GREEN;
                break;
            case MEDIUM:
                typeColor = Ansi.FG_BLUE;
                break;
            case HARD:
                typeColor = Ansi.FG_RED;
                break;
            default:
                typeColor = "";
                break;
        }

        System.out.println("Game type: " + typeColor + gameType.name() + Ansi.RESET);
        System.out.println("Elapsed time: " + time);
        System.out.println("Number of clicks: " + primaryClikNumber);
        System.out.println("Ratio: Discovered " + ratio + " cells by each click");

        System.out.println("\nDiscovered history:");
        Table table = new Table();
        Row r1 = new Row();
        Row r2 = new Row();
        r1.add(new Cell("t(s)"));
        r2.add(new Cell("%"));
        for(int i=0;i<discoveredHistory.size();i++){
            r1.add(new Cell(String.valueOf(i)));
            r2.add(new Cell(String.valueOf(discoveredHistory.get(i))));
        }
        table.add(r1);
        table.add(r2);
        table.setFirstRowAsHeader(true);
        table.setDistanceBetweenCells(2);
        table.print();

    }

    private String readOption() {
        if (readOnly) {
            System.out.println("\nENTER=Back");
        } else {
            System.out.println("\nS=Save, ENTER=Back");
        }
        System.out.print("Type an option: ");

        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        }
        catch (IOException ex) {
            Logger.getLogger(ChooseGameViewCMD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
