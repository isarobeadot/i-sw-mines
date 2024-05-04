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
