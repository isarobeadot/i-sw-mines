package inaki.sw.mines;

import inaki.sw.mines.controller.Controller;
import java.io.IOException;

/**
 *
 * @author inaki
 */
public class Mines {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        final Controller c = new Controller();
        c.startController();
    }

}
