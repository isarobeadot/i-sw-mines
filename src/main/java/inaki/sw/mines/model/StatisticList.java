package inaki.sw.mines.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class StatisticList extends ArrayList<Statistic> {

    /**
     *
     */
    public StatisticList() {
        super();
    }

    /**
     *
     * @param c
     */
    public StatisticList(Collection<Statistic> c) {
        super(c);
    }
}
