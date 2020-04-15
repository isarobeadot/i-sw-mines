package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.Board;
import inaki.sw.mines.model.Clue;

/**
 *
 * @author inaki
 */
public interface MainViewInterface extends ViewInterface {

    static final String MV_NEW = "MV_NEW";
    static final String MV_RESTART = "MV_RESTART";
    static final String MV_CHRONO = "MV_CHRONO";
    static final String MV_SOLVE = "MV_SOLVE";
    static final String MV_LOST = "MV_LOST";
    static final String MV_WIN = "MV_WIN";
    static final String MV_START_CHRONO = "MV_START_CHRONO";
    static final String MV_CLUE_WHITE_AREA = "MV_CLUE_WHITE_AREA";
    static final String MV_CLUE_A_NUMBER = "MV_CLUE_A_NUMBER";
    static final String MV_CLUE_A_FLAG = "MV_CLUE_A_FLAG";

    @Override
    public void setController(final Controller c);

    @Override
    public void startView();

    @Override
    public void hideView();

    public int getHorizontal();

    public int getVertical();

    public int getMines();

    public int getPrimaryClikNumber();

    public int getSecondaryClikNumber();

    public void setBoard(final Board b);

    public void solveBoard();

    public void updateChronometer(int m, int s);

    public int getDiscoveredPrecentage();

    public void setReadOnly(boolean readOnly);

    public void showClue(final Clue clue);
}
