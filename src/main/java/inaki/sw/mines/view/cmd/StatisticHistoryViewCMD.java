package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.Statistic;
import inaki.sw.mines.model.StatisticList;
import inaki.sw.mines.view.IStatisticHistoryView;
import inaki.sw.mines.view.cmd.utils.Ansi;
import inaki.sw.mines.view.cmd.utils.table.Cell;
import inaki.sw.mines.view.cmd.utils.table.Row;
import inaki.sw.mines.view.cmd.utils.table.Table;
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
    private StatisticList statisticSet;
    private String version;

    @Override
    public void setStatistics(StatisticList set) {
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
        System.out.print(Ansi.CLS + Ansi.HOME);
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
        Table table = new Table();
        table.setFirstRowAsHeader(true);
        table.setDistanceBetweenCells(2);

        Row header = new Row();
        header.add(new Cell("Date"));
        header.add(new Cell("Type"));
        header.add(new Cell("Name"));
        header.add(new Cell("Time"));
        header.add(new Cell("Size"));
        header.add(new Cell("Mines"));
        table.add(header);

        statisticSet.forEach(s -> {
            String typeColor;
            switch (s.getType()) {
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
            Row row = new Row();
            row.add(new Cell(s.getWinDate().toString()));
            row.add(new Cell(s.getType().name(), typeColor));
            row.add(new Cell(s.getName()));
            row.add(new Cell(s.getTotalMinutes() + ":" + s.getTotalSeconds()));
            row.add(new Cell(s.getHorizontalSize() + "x" + s.getVerticalSize()));
            row.add(new Cell(s.getMineNumber() + ""));
            table.add(row);
        });
        table.print();
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
