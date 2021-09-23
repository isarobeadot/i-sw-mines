package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public interface IChooseGameView extends IView {

    static final String CGV_EASY = "CGV_EASY";
    static final String CGV_MEDIUM = "CGV_MEDIUM";
    static final String CGV_HARD = "CGV_HARD";
    static final String CGV_START = "CGV_START";
    static final String CGV_STATISTICS = "CGV_STATISTICS";

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
     * @param b
     */
    public void enableStatistics(final boolean b);

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
     * @param version 
     */
    public void setVersion(String version);
}
