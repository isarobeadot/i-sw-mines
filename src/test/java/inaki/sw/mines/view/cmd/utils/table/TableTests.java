package inaki.sw.mines.view.cmd.utils.table;

import java.util.Random;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class TableTests {

    private static final int MAX_COLS = 100;
    private static final int MAX_ROWS = 100;
    private static final int CELL_DISTANCE = 5;

    private Table table;
    private static Random rand;

    @BeforeAll
    public static void setUpClass() {
        rand = new Random();
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        table = new Table();
    }

    @Test
    void testEmptyTable() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        assertEquals(0, table.columns());
        assertEquals(0, table.rows());
    }

    @Test
    void testAddEmptyRows() {
        int rows = addEmptyRows(rand.nextInt(MAX_ROWS) + 1);
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName() + ": rows = " + rows);

        assertEquals(0, table.columns());
        assertEquals(rows, table.rows());
    }

    @Test
    void testAddRowsWithVariableCols() {
        int rows = rand.nextInt(MAX_ROWS) + 1;
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName() + ": rows = " + rows);

        int totalCols = 0;
        for (int i = 0; i < rows; i++) {
            int cols = addRow(rand.nextInt(MAX_COLS) + 1);
            System.out.println(new Object() {
            }.getClass().getEnclosingMethod().getName() + ": cols = " + cols);
            totalCols = Math.max(cols, totalCols);
        }
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName() + ": totalCols = " + totalCols);

        assertEquals(totalCols, table.columns());
        assertEquals(rows, table.rows());
    }

    @Test
    void testPrintWithFirstRowAsHeaderTrueAndOuterBordersTrueAndRowNumbersFalse() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(0);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(true);
        table.setOuterBorders(true);
        table.setRowNumbersLeft(false);
        table.setRowNumbersRight(false);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderFalseAndOuterBordersTrueAndRowNumbersFalse() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(0);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(false);
        table.setOuterBorders(true);
        table.setRowNumbersLeft(false);
        table.setRowNumbersRight(false);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderTrueAndOuterBordersFalseAndRowNumbersFalse() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(0);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(true);
        table.setOuterBorders(false);
        table.setRowNumbersLeft(false);
        table.setRowNumbersRight(false);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderFalseAndOuterBordersFalseAndRowNumbersFalse() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(0);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(false);
        table.setOuterBorders(false);
        table.setRowNumbersLeft(false);
        table.setRowNumbersRight(false);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderTrueAndOuterBordersTrueAndRowNumbersTrue() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(3);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(true);
        table.setOuterBorders(true);
        table.setRowNumbersLeft(true);
        table.setRowNumbersRight(true);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderFalseAndOuterBordersTrueAndRowNumbersTrue() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(3);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(false);
        table.setOuterBorders(true);
        table.setRowNumbersLeft(true);
        table.setRowNumbersRight(true);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderTrueAndOuterBordersFalseAndRowNumbersTrue() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(3);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(true);
        table.setOuterBorders(false);
        table.setRowNumbersLeft(true);
        table.setRowNumbersRight(true);

        table.print();
    }

    @Test
    void testPrintWithFirstRowAsHeaderFalseAndOuterBordersFalseAndRowNumbersTrue() {
        System.out.println(new Object() {
        }.getClass().getEnclosingMethod().getName());

        someCustomRows(3);

        table.setDistanceBetweenCells(CELL_DISTANCE);
        table.setFirstRowAsHeader(false);
        table.setOuterBorders(false);
        table.setRowNumbersLeft(true);
        table.setRowNumbersRight(true);

        table.print();
    }

    private int addEmptyRows(int rows) {
        for (int i = 0; i < rows; i++) {
            table.add(new Row());
        }
        return rows;
    }

    private int addRow(int cols) {
        Row row = new Row();
        for (int i = 0; i < cols; i++) {
            row.add(new Cell(""));
        }
        table.add(row);
        return cols;
    }

    private void someCustomRows(int repeat) {
        Row row1 = new Row();
        row1.add(new Cell("ab"));
        row1.add(new Cell("cde"));
        row1.add(new Cell("fg"));

        Row row2 = new Row();
        row2.add(new Cell("awrefg"));
        row2.add(new Cell("er"));
        row2.add(new Cell("hdeshsd"));

        Row row3 = new Row();
        row3.add(new Cell("hgj"));
        row3.add(new Cell("evvtrawl"));
        row3.add(new Cell("sra"));

        table.add(row1);
        table.add(row2);
        table.add(row3);

        for (int i = 0; i < repeat; i++) {
            table.add(row1);
            table.add(row2);
            table.add(row3);
        }
    }

}
