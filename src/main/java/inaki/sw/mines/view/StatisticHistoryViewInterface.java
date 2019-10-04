package inaki.sw.mines.view;

import inaki.sw.mines.model.StatisticSet;

/**
 *
 * @author inaki
 */
public interface StatisticHistoryViewInterface extends ViewInterface {
    
    static final String SHV_DETAIL = "SHV_DETAIL";

    public void setStatistics(StatisticSet set);
    
}
