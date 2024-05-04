package inaki.sw.mines.view.cmd.utils.table;

import inaki.sw.mines.view.cmd.utils.Ansi;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class Cell {

    private String textColor;
    private String value;

    /**
     *
     * @param value
     */
    public Cell(String value) {
        this.value = value;
    }

    /**
     *
     * @param value
     * @param textColor
     */
    public Cell(String value, String textColor) {
        this.textColor = textColor;
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String getTextColor() {
        return textColor;
    }

    /**
     *
     * @param textColor
     */
    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    /**
     *
     * @return
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public int width() {
        return value == null ? 0 : value.length();
    }

    @Override
    public String toString() {
        return (textColor == null ? "" : textColor) + value + Ansi.RESET;
    }
}
