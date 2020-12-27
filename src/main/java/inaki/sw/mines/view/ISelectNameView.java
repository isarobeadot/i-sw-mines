package inaki.sw.mines.view;

import java.util.List;

/**
 *
 * @author inaki
 */
public interface ISelectNameView extends IView {

    static final String SNV_OK = "SNV_OK";

    public String getSelectedName();

    public void setSavedNameSet(List<String> names);

}
