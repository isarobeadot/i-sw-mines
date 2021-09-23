package inaki.sw.mines;

import inaki.sw.mines.controller.Controller;
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
        final Controller c = new Controller();
        if (args.length > 0 && args[0].equals("--testing")) {
            c.testingConfig();
        }
        c.startController();
    }

}
