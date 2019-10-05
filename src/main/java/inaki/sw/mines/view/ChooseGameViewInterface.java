package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;

/**
 *
 * @author inaki
 */
public interface ChooseGameViewInterface extends ViewInterface {

    static final String CGV_EASY = "CGV_EASY";
    static final String CGV_MEDIUM = "CGV_MEDIUM";
    static final String CGV_HARD = "CGV_HARD";
    static final String CGV_START = "CGV_START";
    static final String CGV_STATISTICS = "CGV_STATISTICS";

    @Override
    public void setController(final Controller c);

    @Override
    public void startView();

    @Override
    public void hideView();

    public void enableStatistics(final boolean b);

    public int getHorizontal();

    public int getVertical();

    public int getMines();
}
