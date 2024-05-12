package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.GameType;
import java.util.List;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public interface IStatisticsView extends IView {

    static final String SV_SAVE = "SV_SAVE";
    static final String SV_OK = "SV_OK";

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
     * @param m
     * @param s
     */
    public void setTime(int m, int s);

    /**
     *
     * @param clikNo
     */
    public void setPrimaryClikNumber(int clikNo);

    /**
     *
     * @param ratio
     */
    public void setRatio(double ratio);

    /**
     *
     * @param discoveredHistory
     */
    public void setDiscoveredHistory(List<Integer> discoveredHistory);

    /**
     *
     * @param type
     */
    public void setGameType(GameType type);

    /**
     *
     * @param readOnly
     */
    public void setReadOnly(boolean readOnly);

}
