package inaki.sw.mines.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import inaki.sw.lib.utils.VChecker;
import static inaki.sw.lib.utils.VChecker.VC_VERSION_AVAILABLE;
import inaki.sw.mines.model.Board;
import inaki.sw.mines.model.Chronometer;
import static inaki.sw.mines.model.Chronometer.C_UPDATE_CHRONO;
import inaki.sw.mines.model.Clue;
import inaki.sw.mines.model.GameType;
import inaki.sw.mines.model.Statistic;
import inaki.sw.mines.model.StatisticSet;
import inaki.sw.mines.view.IChooseGameView;
import static inaki.sw.mines.view.IChooseGameView.CGV_EASY;
import static inaki.sw.mines.view.IChooseGameView.CGV_HARD;
import static inaki.sw.mines.view.IChooseGameView.CGV_MEDIUM;
import static inaki.sw.mines.view.IChooseGameView.CGV_START;
import static inaki.sw.mines.view.IChooseGameView.CGV_STATISTICS;
import inaki.sw.mines.view.IMainView;
import static inaki.sw.mines.view.IMainView.MV_CHRONO;
import static inaki.sw.mines.view.IMainView.MV_CHRONO_PAUSE;
import static inaki.sw.mines.view.IMainView.MV_CLUE_A_FLAG;
import static inaki.sw.mines.view.IMainView.MV_CLUE_A_NUMBER;
import static inaki.sw.mines.view.IMainView.MV_CLUE_WHITE_AREA;
import static inaki.sw.mines.view.IMainView.MV_LOST;
import static inaki.sw.mines.view.IMainView.MV_NEW;
import static inaki.sw.mines.view.IMainView.MV_RESTART;
import static inaki.sw.mines.view.IMainView.MV_SOLVE;
import static inaki.sw.mines.view.IMainView.MV_START_CHRONO;
import static inaki.sw.mines.view.IMainView.MV_WIN;
import inaki.sw.mines.view.ISelectNameView;
import static inaki.sw.mines.view.ISelectNameView.SNV_OK;
import inaki.sw.mines.view.IStatisticHistoryView;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_CUSTOM;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_DETAIL;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_EASY;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_HARD;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_MEDIUM;
import static inaki.sw.mines.view.IStatisticHistoryView.SHV_OK;
import inaki.sw.mines.view.IStatisticsView;
import static inaki.sw.mines.view.IStatisticsView.SV_OK;
import static inaki.sw.mines.view.IStatisticsView.SV_SAVE;
import inaki.sw.mines.view.ViewMode;
import inaki.sw.mines.view.cmd.ChooseGameViewCMD;
import inaki.sw.mines.view.cmd.MainViewCMD;
import inaki.sw.mines.view.cmd.SelectNameViewCMD;
import inaki.sw.mines.view.cmd.StatisticHistoryViewCMD;
import inaki.sw.mines.view.cmd.StatisticsViewCMD;
import inaki.sw.mines.view.swing.ChooseGameView;
import inaki.sw.mines.view.swing.MainView;
import inaki.sw.mines.view.swing.SelectNameView;
import inaki.sw.mines.view.swing.StatisticHistoryView;
import inaki.sw.mines.view.swing.StatisticsView;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class Controller implements ActionListener {

    private static final Logger LOGGER = getLogger(Controller.class.getName());

    private final ViewMode viewMode;
    private final String version = this.getClass().getPackage().getImplementationVersion();
    private IChooseGameView cgv;
    private IMainView mv;
    private ISelectNameView snv;
    private IStatisticHistoryView shv;
    private IStatisticsView sv;

    private List<Integer> discoveredHistory = new ArrayList<>();
    private Board board;
    private GameType type;
    private StatisticSet statistics = new StatisticSet();

    private final Chronometer chrono;
    private final VChecker vChecker;

    /**
     * @param viewMode View mode
     * @throws IOException
     */
    public Controller(ViewMode viewMode) throws IOException {
        if (!viewMode.equals(ViewMode.CMD)) {
            try {
                this.cgv = new ChooseGameView();
                this.mv = new MainView();
                this.snv = new SelectNameView();
                this.shv = new StatisticHistoryView();
                this.sv = new StatisticsView();
            }
            catch (java.awt.HeadlessException ex) {
                viewMode = ViewMode.CMD;
            }
        }
        if (viewMode.equals(ViewMode.CMD)) {
            this.cgv = new ChooseGameViewCMD();
            this.mv = new MainViewCMD();
            this.snv = new SelectNameViewCMD();
            this.shv = new StatisticHistoryViewCMD();
            this.sv = new StatisticsViewCMD();
        }
        this.viewMode = viewMode;
        this.chrono = new Chronometer();
        this.vChecker = new VChecker("isw-mines", this.version);
        this.statistics = readStatisticSet();
    }

    /**
     * Returns the board instance. (For testing only)
     *
     * @return
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Starts the controller.
     */
    public void startController() {
        if (viewMode.equals(ViewMode.DEBUG)) {
            debugConfig();
        }

        cgv.setController(this);
        cgv.setVersion(version);
        mv.setController(this);
        mv.setVersion(version);
        snv.setController(this);
        snv.setVersion(version);
        shv.setController(this);
        shv.setVersion(version);
        sv.setController(this);
        sv.setVersion(version);
        chrono.setActionlistener(this);
        vChecker.setActionlistener(this);

        cgv.enableStatistics(!statistics.isEmpty());
        cgv.startView();

        if (this.version != null) {
            Thread vCheckerThread = new Thread(vChecker);
            vCheckerThread.start();
        }
    }

    /**
     * Disables Nimbus LnF. Use this only for testing.
     */
    private void debugConfig() {
        cgv.disableNimbus(true);
        mv.disableNimbus(true);
        snv.disableNimbus(true);
        shv.disableNimbus(true);
        sv.disableNimbus(true);
    }

    /**
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String[] actions = e.getActionCommand().split(",");
        LOGGER.fine(Arrays.toString(actions));
        switch (actions[0]) {
            case CGV_EASY:
                board = new Board(8, 8, 10);
                type = GameType.EASY;
                doSTART();
                break;
            case CGV_MEDIUM:
                board = new Board(16, 16, 40);
                type = GameType.MEDIUM;
                doSTART();
                break;
            case CGV_HARD:
                board = new Board(30, 16, 99);
                type = GameType.HARD;
                doSTART();
                break;
            case CGV_START:
                board = new Board(cgv.getHorizontal(), cgv.getVertical(),
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
                board = new Board(mv.getHorizontal(), mv.getVertical(),
                        mv.getMines());
                doSTART();
                break;
            case MV_CHRONO:
                if (chrono.isInstanceRunning()) {
                    if (chrono.isInstancePaused()) {
                        chrono.resumeChronometer();
                        mv.setReadOnly(false);
                    } else {
                        chrono.pauseChronometer();
                        mv.setReadOnly(true);
                    }
                }
                break;
            case MV_CHRONO_PAUSE:
                if (chrono.isInstanceRunning()) {
                    chrono.pauseChronometer();
                    mv.setReadOnly(true);
                }
                break;
            case MV_SOLVE:
                mv.solveBoard();
                break;
            case MV_LOST:
                chrono.stopChronometer();
                if (!this.viewMode.equals(ViewMode.CMD)) {
                    showMessageDialog((Component) mv, "You lost the game :(", "Game lost", ERROR_MESSAGE);
                }
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
                Thread chronoThread = new Thread(chrono);
                chronoThread.start();
                discoveredHistory.add(0);
                break;
            case MV_CLUE_WHITE_AREA:
                mv.showClue(Clue.WHITE_AREA);
                break;
            case MV_CLUE_A_NUMBER:
                mv.showClue(Clue.NUMBER);
                break;
            case MV_CLUE_A_FLAG:
                mv.showClue(Clue.FLAG);
                break;
            case C_UPDATE_CHRONO:
                mv.updateChronometer(chrono.getMinutes(), chrono.getSeconds());
                discoveredHistory.add(mv.getDiscoveredPrecentage());
                break;
            case SV_SAVE:
                snv.setSavedNameSet(statistics.stream().map(s -> s.getName()).collect(Collectors.toList()));
                snv.startView();
                break;
            case SV_OK:
                sv.hideView();
                break;
            case SNV_OK:
                String name = viewMode.equals(ViewMode.DEBUG) ? "test" : snv.getSelectedName();
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
                s.setViewMode(viewMode);
                s.setWinDate(new Date());
                statistics.add(s);
                saveStatisticSet(statistics);
                sv.setReadOnly(true);
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
            case SHV_OK:
                shv.hideView();
                break;
            case VC_VERSION_AVAILABLE:
                final int option = JOptionPane.showConfirmDialog((Component) cgv, "<html><p>There is a newer version available.</p>"
                        + "<p>Do you want to download it?</p></html>", "New version", JOptionPane.YES_NO_OPTION);
                if (option == 0) {
                    // YES
                    try {
                        Desktop.getDesktop().browse(URI.create("https://inaki-sw.xyz/web/downloads#isw-mines"));
                    }
                    catch (IOException ex) {
                        if (this.viewMode.equals(ViewMode.CMD)) {
                            // TODO
                        } else {
                            JOptionPane.showMessageDialog((Component) cgv, "An error occurred", "Error", JOptionPane.ERROR_MESSAGE);
                        }
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
        if (board != null) {
            board.generate(viewMode.equals(ViewMode.DEBUG));
        }
        mv.setBoard(board);
        mv.startView();
        chrono.stopChronometer();
        chrono.resumeChronometer();
        discoveredHistory = new ArrayList<>();
    }

    private StatisticSet readStatisticSet() {
        ObjectMapper mapper = new ObjectMapper();
        StatisticSet set = new StatisticSet();
        try {
            set = mapper.readValue(new File("statistics.json"), StatisticSet.class);
            set.forEach(s -> {
                // Since 2.5
                if (s.getViewMode() == null) {
                    if ("test".equals(s.getName())) {
                        s.setViewMode(ViewMode.DEBUG);
                    } else {
                        s.setViewMode(ViewMode.SWING);
                    }
                }
            });
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
