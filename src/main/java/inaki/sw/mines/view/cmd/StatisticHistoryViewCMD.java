package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.Statistic;
import inaki.sw.mines.model.StatisticSet;
import inaki.sw.mines.view.IStatisticHistoryView;
import java.awt.event.ActionEvent;
import static java.awt.event.KeyEvent.VK_S;
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
public class StatisticHistoryViewCMD implements IStatisticHistoryView {

    private Controller c;
    private StatisticSet statisticSet;
    private String version;

    @Override
    public void setStatistics(StatisticSet set) {
        this.statisticSet = set;
    }

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
            c.actionPerformed(new ActionEvent(this, VK_S, SHV_OK));
        }
    }

    @Override
    public void hideView() {
        System.out.print(ANSI_CLS + ANSI_HOME);
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
        System.out.println("Date\t\t\t\tType\tName\tTime\tSize\tMines");
        for (Statistic s : statisticSet) {
            String type;
            switch (s.getType()) {
                case EASY:
                    type = ANSI_FG_GREEN + s.getType() + ANSI_RESET;
                    break;
                case MEDIUM:
                    type = ANSI_FG_BLUE + s.getType() + ANSI_RESET;
                    break;
                case HARD:
                    type = ANSI_FG_RED + s.getType() + ANSI_RESET;
                    break;
                default:
                    type = s.getType().name();
                    break;
            }
            System.out.println(s.getWinDate() + "\t" + type + "\t" + s.getName() + "\t" + s.getTotalMinutes() + ":" + s.getTotalSeconds() + "\t" + s.getHorizontalSize() + "x" + s.getVerticalSize() + "\t" + s.getMineNumber());
        }
    }

    private String readOption() {
        System.out.println("\nENTER=Back");
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
