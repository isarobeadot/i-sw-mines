package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.Board;
import inaki.sw.mines.model.Clue;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public interface IMainView extends IView {

    static final String MV_NEW = "MV_NEW";
    static final String MV_RESTART = "MV_RESTART";
    static final String MV_CHRONO = "MV_CHRONO";
    static final String MV_CHRONO_PAUSE = "MV_CHRONO_PAUSE";
    static final String MV_SOLVE = "MV_SOLVE";
    static final String MV_LOST = "MV_LOST";
    static final String MV_WIN = "MV_WIN";
    static final String MV_START_CHRONO = "MV_START_CHRONO";
    static final String MV_CLUE_WHITE_AREA = "MV_CLUE_WHITE_AREA";
    static final String MV_CLUE_A_NUMBER = "MV_CLUE_A_NUMBER";
    static final String MV_CLUE_A_FLAG = "MV_CLUE_A_FLAG";
    
    static final String MV_FLAG_TXT = "<html><body>&para;</body></html";

    /**
     *
     * @param c
     */
    @Override
    public void setController(final Controller c);

    /**
     *
     */
    @Override
    public void startView();

    /**
     *
     */
    @Override
    public void hideView();

    /**
     *
     * @return
     */
    public int getHorizontal();

    /**
     *
     * @return
     */
    public int getVertical();

    /**
     *
     * @return
     */
    public int getMines();

    /**
     *
     * @return
     */
    public int getPrimaryClikNumber();

    /**
     *
     * @return
     */
    public int getSecondaryClikNumber();

    /**
     *
     * @param b
     */
    public void setBoard(final Board b);

    /**
     *
     */
    public void solveBoard();

    /**
     *
     * @param m
     * @param s
     */
    public void updateChronometer(int m, int s);

    /**
     *
     * @return
     */
    public int getDiscoveredPrecentage();

    /**
     *
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly);

    /**
     *
     * @param clue
     */
    public void showClue(final Clue clue);
}
