package inaki.sw.mines.view.cmd;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.view.ISelectNameView;
import inaki.sw.mines.view.cmd.utils.Ansi;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author inaki
 * @since 2.5
 */
public class SelectNameViewCMD implements ISelectNameView {

    private Controller c;
    private List<String> savedNames;
    private String selectedName;
    private String version;

    @Override
    public String getSelectedName() {
        return this.selectedName;
    }

    @Override
    public void setSavedNameSet(List<String> names) {
        this.savedNames = names;
    }

    @Override
    public void setController(Controller c) {
        this.c = c;
    }

    @Override
    public void startView() {
        showTitle();
        selectedName = readName();
        c.actionPerformed(new ActionEvent(this, 0, SNV_OK));
    }

    @Override
    public void hideView() {
        System.out.print(Ansi.CLS + Ansi.HOME);
    }

    @Override
    public void setVersion(String version) {
        this.version = version != null ? version : "";
    }

    @Override
    public void disableNimbus(boolean disableNimbus) {
        // empty
    }

    private void showTitle() {
        System.out.print(Ansi.CLS + Ansi.HOME);
        String s = Ansi.FG_CYAN + Ansi.BOLD + "\n" + "+-";
        String title = TITLE.toUpperCase() + (version.equals("") ? "" : " v" + version);
        for (int i = 0; i < title.length(); i++) {
            s += "-";
        }
        s += "-+\n";
        System.out.println(s + "| " + title + " |" + s + Ansi.RESET);
    }

    private String readName() {
        System.out.print("Select your name: ");

        try {
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            return in.readLine();
        }
        catch (IOException ex) {
            Logger.getLogger(ChooseGameViewCMD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
