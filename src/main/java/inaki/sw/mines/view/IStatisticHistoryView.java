package inaki.sw.mines.view;

import inaki.sw.mines.model.StatisticSet;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public interface IStatisticHistoryView extends IView {

    static final String SHV_DETAIL = "SHV_DETAIL";
    static final String SHV_NONE = "SHV_NONE";
    static final String SHV_EASY = "SHV_EASY";
    static final String SHV_MEDIUM = "SHV_MEDIUM";
    static final String SHV_HARD = "SHV_HARD";
    static final String SHV_CUSTOM = "SHV_CUSTOM";
    static final String SHV_OK = "SHV_OK";

    /**
     *
     * @param set
     */
    public void setStatistics(StatisticSet set);

}
