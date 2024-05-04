package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.view.IStatisticsView;
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
        System.out.print(ANSI_CLS + ANSI_HOME);
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
        System.out.print(ANSI_CLS + ANSI_HOME);
        String s = ANSI_FG_CYAN + ANSI_BOLD + "\n" + "+-";
        String title = TITLE.toUpperCase() + (version.equals("") ? "" : " v" + version);
        for (int i = 0; i < title.length(); i++) {
            s += "-";
        }
        s += "-+\n";
        System.out.println(s + "| " + title + " |" + s + ANSI_RESET);
    }

    private void showStatistics() {
        System.out.println("Elapsed time: " + time);
        System.out.println("Number of clicks: " + primaryClikNumber);
        System.out.println("Ratio: Discovered " + ratio + " cells by each click");
        // TODO discoveredHistory
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
