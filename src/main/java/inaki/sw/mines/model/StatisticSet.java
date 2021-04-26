package inaki.sw.mines.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class StatisticSet extends ArrayList<Statistic> {

    /**
     *
     */
    public StatisticSet() {
        super();
    }

    /**
     *
     * @param c
     */
    public StatisticSet(Collection<Statistic> c) {
        super(c);
    }
}
