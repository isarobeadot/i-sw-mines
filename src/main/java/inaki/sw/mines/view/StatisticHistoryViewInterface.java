package inaki.sw.mines.view;

import inaki.sw.mines.model.StatisticSet;

/**
 *
 * @author inaki
 */
public interface StatisticHistoryViewInterface extends ViewInterface {
    
    static final String SHV_DETAIL = "SHV_DETAIL";
    static final String SHV_NONE = "SHV_NONE";
    static final String SHV_EASY = "SHV_EASY";
    static final String SHV_MEDIUM = "SHV_MEDIUM";
    static final String SHV_HARD = "SHV_HARD";
    static final String SHV_CUSTOM = "SHV_CUSTOM";

    public void setStatistics(StatisticSet set);
    
}
