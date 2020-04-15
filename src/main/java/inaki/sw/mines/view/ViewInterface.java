package inaki.sw.mines.view;

import inaki.sw.mines.controller.Controller;

/**
 *
 * @author inaki
 */
interface ViewInterface {

    public void setController(final Controller c);

    public void startView();

    public void hideView();

    public void disableNimbus(boolean disableNimbus);
}
