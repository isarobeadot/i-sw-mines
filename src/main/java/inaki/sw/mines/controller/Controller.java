package inaki.sw.mines.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inaki.sw.mines.model.Board;
import inaki.sw.mines.model.Chronometer;
import static inaki.sw.mines.model.Chronometer.C_UPDATE_CHRONO;
import inaki.sw.mines.model.GameType;
import inaki.sw.mines.model.Statistic;
import inaki.sw.mines.model.StatisticSet;
import inaki.sw.mines.view.ChooseGameViewInterface;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_EASY;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_HARD;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_MEDIUM;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_START;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_STATISTICS;
import inaki.sw.mines.view.MainViewInterface;
import static inaki.sw.mines.view.MainViewInterface.MV_LOST;
import static inaki.sw.mines.view.MainViewInterface.MV_NEW;
import static inaki.sw.mines.view.MainViewInterface.MV_RESTART;
import static inaki.sw.mines.view.MainViewInterface.MV_SOLVE;
import static inaki.sw.mines.view.MainViewInterface.MV_START_CHRONO;
import static inaki.sw.mines.view.MainViewInterface.MV_WIN;
import inaki.sw.mines.view.SelectNameViewInterface;
import static inaki.sw.mines.view.SelectNameViewInterface.SNV_OK;
import inaki.sw.mines.view.StatisticHistoryViewInterface;
import static inaki.sw.mines.view.StatisticsViewInterface.SV_SAVE;
import inaki.sw.mines.view.swing.ChooseGameView;
import inaki.sw.mines.view.swing.MainView;
import inaki.sw.mines.view.swing.SelectNameView;
import inaki.sw.mines.view.swing.StatisticsView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import inaki.sw.mines.view.StatisticsViewInterface;
import inaki.sw.mines.view.swing.StatisticHistoryView;

/**
 *
 * @author inaki
 */
public class Controller implements java.awt.event.ActionListener {

    private static final Logger LOGGER = getLogger(Controller.class.getName());

    private final ChooseGameViewInterface cgv;
    private final MainViewInterface mv;
    private final SelectNameViewInterface snv;
    private final List<Integer> discoveredHistory = new ArrayList<>();
    private Board b;
    private GameType type;

    private final Chronometer chrono;
    private Thread chronoThread;

    /**
     *
     * @throws java.io.IOException
     */
    public Controller() throws IOException {
        this.cgv = new ChooseGameView();
        this.mv = new MainView();
        this.snv = new SelectNameView();
        this.chrono = new Chronometer();
    }

    /**
     *
     */
    public void startController() {
        cgv.setController(this);
        mv.setController(this);
        snv.setController(this);
        chrono.setActionlistener(this);

        cgv.enableStatistics(!readStatisticSet().isEmpty());
        cgv.startView();
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        LOGGER.info(e.getActionCommand());
        switch (e.getActionCommand()) {
            case CGV_EASY:
                b = new Board(8, 8, 10);
                type = GameType.EASY;
                doSTART();
                break;
            case CGV_MEDIUM:
                b = new Board(16, 16, 40);
                type = GameType.MEDIUM;
                doSTART();
                break;
            case CGV_HARD:
                b = new Board(30, 16, 99);
                type = GameType.HARD;
                doSTART();
                break;
            case CGV_START:
                b = new Board(cgv.getHorizontal(), cgv.getVertical(),
                        cgv.getMines());
                type = GameType.CUSTOM;
                doSTART();
                break;
            case CGV_STATISTICS:
                StatisticHistoryViewInterface shv = new StatisticHistoryView();
                shv.setController(this);
                shv.setStatistics(readStatisticSet());
                shv.startView();
                break;
            case MV_NEW:
                cgv.enableStatistics(!readStatisticSet().isEmpty());
                cgv.startView();
                break;
            case MV_RESTART:
                b = new Board(mv.getHorizontal(), mv.getVertical(),
                        mv.getMines());
                doSTART();
                break;
            case MV_SOLVE:
                mv.solveBoard();
                break;
            case MV_LOST:
                chrono.stopChronometer();
                showMessageDialog((java.awt.Component) mv, "You lost the game :(", "Game lost", ERROR_MESSAGE);
                break;
            case MV_WIN:
                chrono.stopChronometer();
                mv.solveBoard();
                discoveredHistory.add(100);
                StatisticsViewInterface sv = new StatisticsView();
                sv.setController(this);
                sv.setTime(chrono.getMinutes(), chrono.getSeconds());
                sv.setPrimaryClikNumber(mv.getPrimaryClikNumber());
                sv.setRatio(Double.valueOf(mv.getHorizontal() * mv.getVertical() - mv.getMines()) / Double.valueOf(mv.getPrimaryClikNumber()));
                sv.setDiscoveredHistory(discoveredHistory);
                sv.startView();
                break;
            case MV_START_CHRONO:
                chronoThread = new Thread(chrono);
                chronoThread.start();
                discoveredHistory.add(0);
                break;
            case C_UPDATE_CHRONO:
                mv.updateChronometer(chrono.getMinutes(), chrono.getSeconds());
                discoveredHistory.add(mv.getDiscoveredPrecentage());
                break;
            case SV_SAVE:
                snv.startView();
                break;
            case SNV_OK:
                String name = snv.getSelectedName();
                snv.hideView();
                StatisticSet set = readStatisticSet();
                Statistic s = new Statistic();
                s.setDiscoveredHistory(discoveredHistory);
                s.setHorizontalSize(mv.getHorizontal());
                s.setMineNumber(mv.getMines());
                s.setName(name);
                s.setPrimaryClikNumber(mv.getPrimaryClikNumber());
                s.setTotalMinutes(chrono.getMinutes());
                s.setTotalSeconds(chrono.getSeconds());
                s.setType(type);
                s.setVerticalSize(mv.getVertical());
                s.setWinDate(new Date());
                set.add(s);
                saveStatisticSet(set);
                break;
            default:
                LOGGER.warning("Action not supported");
                break;
        }
    }

    private void doSTART() {
        cgv.hideView();
        if (b != null) {
            b.generate();
        }
        mv.setBoard(b);
        mv.startView();
        chrono.stopChronometer();
        discoveredHistory.clear();
    }

    private StatisticSet readStatisticSet() {
        ObjectMapper mapper = new ObjectMapper();
        StatisticSet set = new StatisticSet();
        try {
            set = mapper.readValue(new File("statistics.json"), StatisticSet.class);
        }
        catch (IOException ex) {
            LOGGER.warning(ex.getMessage());
        }
        return set;
    }

    private void saveStatisticSet(StatisticSet set) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("statistics.json"), set);
        }
        catch (IOException ex) {
            LOGGER.severe(ex.getMessage());
        }
    }

}
