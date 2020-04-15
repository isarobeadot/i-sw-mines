package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;
import java.util.List;

/**
 *
 * @author inaki
 */
public interface StatisticsViewInterface extends ViewInterface {

    static final String SV_SAVE = "SV_SAVE";
    static final String SV_OK = "SV_OK";

    @Override
    public void setController(final Controller c);

    @Override
    public void startView();

    @Override
    public void hideView();

    public void setTime(int m, int s);

    public void setPrimaryClikNumber(int clikNo);

    public void setRatio(double ratio);

    public void setDiscoveredHistory(List<Integer> discoveredHistory);

    public void setReadOnly(boolean readOnly);

}
