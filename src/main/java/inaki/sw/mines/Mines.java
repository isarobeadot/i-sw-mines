package inaki.sw.mines;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.view.ViewMode;
import java.io.IOException;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class Mines {

    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        ViewMode viewMode = ViewMode.SWING;
        if (args.length > 0) {
            viewMode = switch (args[0]) {
                case "--debug" ->
                    ViewMode.DEBUG;
                case "--cmd" ->
                    ViewMode.CMD;
                default ->
                    ViewMode.SWING;
            };
        }
        final Controller c = new Controller(viewMode);
        c.startController();
    }

}
