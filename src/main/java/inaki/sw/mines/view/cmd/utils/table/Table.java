package inaki.sw.mines.view.cmd.utils.table;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class Table {

    private final List<Row> rows;
    private final List<Integer> colSizes;
    private int distanceBetweenCells;
    private boolean firstRowAsHeader;
    private boolean outerBorders;
    private boolean rowNumbersLeft;
    private boolean rowNumbersRight;

    /**
     *
     */
    public Table() {
        rows = new ArrayList<>();
        colSizes = new ArrayList<>();
        this.distanceBetweenCells = 1;
    }

    public boolean add(Row row) {
        for (int i = 0; i < row.size(); i++) {
            if (colSizes.size() <= i) {
                colSizes.add(row.get(i).width());
            } else {
                colSizes.set(i, Math.max(row.get(i).width(), colSizes.get(i)));
            }
        }
        return rows.add(row);
    }

    public int columns() {
        return colSizes.size();
    }

    public void forEach(Consumer<? super Row> row) {
        rows.forEach(row);
    }

    public void print() {
        for (int i = 0; i < rows.size(); i++) {
            printRowSeparator(i);
            printRow(i);
        }
        if (outerBorders) {
            printRowSeparator(rows.size());
        }
    }

    private void printRowSeparator(int idx) {
        if (checkFirstRowAsHeader(idx) || checkOuterBorders(idx)) {
            if (rowNumbersLeft) {
                int totalChars = String.valueOf(this.rows.size()).length();
                for (int i = 0; i < totalChars + 3; i++) {
                    System.out.print(" ");
                }
            }
            if (outerBorders) {
                System.out.print("+-");
            }
            colSizes.forEach(size -> {
                for (int i = 0; i < size; i++) {
                    System.out.print(outerBorders ? "-" : "=");
                }
            });
            for (int i = 0; i < (colSizes.size() - 1) * distanceBetweenCells; i++) {
                System.out.print(outerBorders ? "-" : "=");
            }
            if (outerBorders) {
                System.out.print("-+");
            }
            System.out.println();
        }
    }

    private boolean checkFirstRowAsHeader(int idx) {
        return firstRowAsHeader && idx == 1;
    }

    private boolean checkOuterBorders(int idx) {
        return outerBorders && (idx == 0 || idx == rows.size());
    }

    private void printRow(int idx) {
        if (rowNumbersLeft) {
            int totalChars = String.valueOf(this.rows.size()).length();
            int currentChars = String.valueOf(idx).length();
            for (int i = 0; i < totalChars - currentChars; i++) {
                System.out.print(" ");
            }
            System.out.print((firstRowAsHeader && idx == 0) ? "    " : ("(" + idx + ") "));
        }
        if (outerBorders) {
            System.out.print("| ");
        }
        Row row = rows.get(idx);
        for (int j = 0; j < row.size(); j++) {
            Cell cell = row.get(j);
            int separation = colSizes.get(j) - cell.width() + (j == row.size() - 1 ? 1 : distanceBetweenCells);
            String separator = "";
            for (int k = 0; k < separation; k++) {
                separator += " ";
            }
            System.out.print(cell + separator);
        }
        System.out.print(outerBorders ? "|" : "");
        if (rowNumbersRight) {
            if (outerBorders) {
                System.out.print((firstRowAsHeader && idx == 0) ? "" : (" (" + idx + ")"));
            } else {
                System.out.print((firstRowAsHeader && idx == 0) ? "" : ("(" + idx + ")"));
            }
        }
        System.out.println();
    }

    public int rows() {
        return rows.size();
    }

    public void setDistanceBetweenCells(int distanceBetweenCells) {
        this.distanceBetweenCells = Math.max(1, distanceBetweenCells);
    }

    public void setFirstRowAsHeader(boolean firstRowAsHeader) {
        this.firstRowAsHeader = firstRowAsHeader;
    }

    public void setOuterBorders(boolean outerBorders) {
        this.outerBorders = outerBorders;
    }

    public void setRowNumbersLeft(boolean rowNumbersLeft) {
        this.rowNumbersLeft = rowNumbersLeft;
    }

    public void setRowNumbersRight(boolean rowNumbersRight) {
        this.rowNumbersRight = rowNumbersRight;
    }

}
