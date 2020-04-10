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
import static inaki.sw.mines.view.StatisticHistoryViewInterface.SHV_CUSTOM;
import static inaki.sw.mines.view.StatisticHistoryViewInterface.SHV_DETAIL;
import static inaki.sw.mines.view.StatisticHistoryViewInterface.SHV_EASY;
import static inaki.sw.mines.view.StatisticHistoryViewInterface.SHV_HARD;
import static inaki.sw.mines.view.StatisticHistoryViewInterface.SHV_MEDIUM;
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
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 *
 * @author inaki
 */
public class Controller implements ActionListener {

    private static final Logger LOGGER = getLogger(Controller.class.getName());

    private final ChooseGameViewInterface cgv;
    private final MainViewInterface mv;
    private final SelectNameViewInterface snv;
    private final StatisticHistoryViewInterface shv;
    private final StatisticsViewInterface sv;

    private final List<Integer> discoveredHistory = new ArrayList<>();
    private Board b;
    private GameType type;
    private StatisticSet statistics = new StatisticSet();

    private final Chronometer chrono;
    private Thread chronoThread;

    /**
     *
     * @throws IOException
     */
    public Controller() throws IOException {
        this.cgv = new ChooseGameView();
        this.mv = new MainView();
        this.snv = new SelectNameView();
        this.shv = new StatisticHistoryView();
        this.sv = new StatisticsView();
        this.chrono = new Chronometer();
        this.statistics = readStatisticSet();
    }

    /**
     *
     */
    public void startController() {
        cgv.setController(this);
        mv.setController(this);
        snv.setController(this);
        shv.setController(this);
        sv.setController(this);
        chrono.setActionlistener(this);

        cgv.enableStatistics(!statistics.isEmpty());
        cgv.startView();
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] actions = e.getActionCommand().split(",");
        LOGGER.info(Arrays.toString(actions));
        switch (actions[0]) {
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
                shv.setStatistics(statistics);
                shv.startView();
                break;
            case MV_NEW:
                cgv.enableStatistics(!statistics.isEmpty());
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
                showMessageDialog((Component) mv, "You lost the game :(", "Game lost", ERROR_MESSAGE);
                break;
            case MV_WIN:
                chrono.stopChronometer();
                mv.solveBoard();
                discoveredHistory.add(100);
                sv.setReadOnly(false);
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
                statistics.add(s);
                saveStatisticSet(statistics);
                break;
            case SHV_DETAIL:
                if (actions.length == 3) {
                    Statistic sDetail = null;
                    switch (actions[1]) {
                        case SHV_EASY:
                            sDetail = findStatisticByTypeAndDate(statistics, GameType.EASY, actions[2]);
                            break;
                        case SHV_MEDIUM:
                            sDetail = findStatisticByTypeAndDate(statistics, GameType.MEDIUM, actions[2]);
                            break;
                        case SHV_HARD:
                            sDetail = findStatisticByTypeAndDate(statistics, GameType.HARD, actions[2]);
                            break;
                        case SHV_CUSTOM:
                            sDetail = findStatisticByTypeAndDate(statistics, GameType.CUSTOM, actions[2]);
                            break;
                        default:
                            LOGGER.warning("No statistic type found");
                            break;
                    }
                    if (sDetail != null) {
                        sv.setReadOnly(true);
                        sv.setTime(sDetail.getTotalMinutes(), sDetail.getTotalSeconds());
                        sv.setPrimaryClikNumber(sDetail.getPrimaryClikNumber());
                        sv.setRatio(Double.valueOf(sDetail.getHorizontalSize() * sDetail.getVerticalSize() - sDetail.getMineNumber())
                                / Double.valueOf(sDetail.getPrimaryClikNumber()));
                        sv.setDiscoveredHistory(sDetail.getDiscoveredHistory());
                        sv.startView();
                    } else {
                        LOGGER.warning("No statistic found");
                    }
                }
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

    private Statistic findStatisticByTypeAndDate(StatisticSet statistics, GameType type, String dateTime) {
        StatisticSet tmpSet = statistics.stream()
                .filter(x -> x.getType().equals(type) && dateTime.equals(x.getWinDate().getTime() + ""))
                .collect(Collectors.toCollection(StatisticSet::new));
        if (tmpSet.size() == 1) {
            return tmpSet.get(0);
        }
        return null;
    }
}
