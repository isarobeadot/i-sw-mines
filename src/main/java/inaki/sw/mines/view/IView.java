package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
interface IView {

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
     * @param disableNimbus 
     */
    public void disableNimbus(boolean disableNimbus);
}
