package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
interface IView {

    /* Title */
    public final static String TITLE = "I-SW Mines";

    /* Ansi colors, etc. */
    public static final String ANSI_CLS = "\u001b[2J";
    public static final String ANSI_HOME = "\u001b[H";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_FG_BLACK = "\u001B[30m";
    public static final String ANSI_FG_RED = "\u001B[31m";
    public static final String ANSI_FG_GREEN = "\u001B[32m";
    public static final String ANSI_FG_YELLOW = "\u001B[33m";
    public static final String ANSI_FG_BLUE = "\u001B[34m";
    public static final String ANSI_FG_PURPLE = "\u001B[35m";
    public static final String ANSI_FG_CYAN = "\u001B[36m";
    public static final String ANSI_FG_WHITE = "\u001B[37m";
    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    /**
     *
     * @param c
     */
    public void setController(final Controller c);

    /**
     *
     */
    public void startView();

    /**
     *
     */
    public void hideView();

    /**
     *
     * @param version
     */
    public void setVersion(String version);

    /**
     *
     * @param disableNimbus
     */
    public void disableNimbus(boolean disableNimbus);
}
