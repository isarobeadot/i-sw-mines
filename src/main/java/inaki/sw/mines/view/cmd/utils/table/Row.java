package inaki.sw.mines.view.cmd.utils.table;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class Row {

    private final List<Cell> cells;

    /**
     *
     */
    public Row() {
        cells = new ArrayList<>();
    }

    public boolean add(Cell cell) {
        return cells.add(cell);
    }

    public void forEach(Consumer<? super Cell> cell) {
        cells.forEach(cell);
    }

    public Cell get(int idx) {
        return this.cells.get(idx);
    }

    public int size() {
        return cells.size();
    }
}
