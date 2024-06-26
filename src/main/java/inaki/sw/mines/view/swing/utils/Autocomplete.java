package inaki.sw.mines.view.swing.utils;

import java.awt.event.ActionEvent;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import static java.util.logging.Logger.getLogger;
import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 * From https://stackabuse.com/example-adding-autocomplete-to-jtextfield/
 *
 * @author inaki
 * @since 2.2.1
 */
public class Autocomplete implements DocumentListener {

    private static final Logger LOGGER = getLogger(Autocomplete.class.getName());

    private enum Mode {
        INSERT,
        COMPLETION
    }

    private final JTextField textField;
    private final List<String> keywords;
    private Mode mode = Mode.INSERT;

    /**
     *
     * @param textField
     * @param keywords
     */
    public Autocomplete(JTextField textField, List<String> keywords) {
        this.textField = textField;
        this.keywords = keywords;
        Collections.sort(keywords);
    }

    @Override
    public void changedUpdate(DocumentEvent ev) {
        // empty
    }

    @Override
    public void removeUpdate(DocumentEvent ev) {
        // empty
    }

    @Override
    public void insertUpdate(DocumentEvent ev) {
        if (ev.getLength() != 1) {
            return;
        }

        int pos = ev.getOffset();
        String content = "";
        try {
            content = textField.getText(0, pos + 1);
        }
        catch (BadLocationException e) {
            LOGGER.severe(e.getMessage());
        }

        // Find where the word starts
        int w;
        for (w = pos; w >= 0; w--) {
            if (!Character.isLetter(content.charAt(w))) {
                break;
            }
        }

        // Too few chars
        if (pos - w < 2) {
            return;
        }

        String prefix = content.substring(w + 1).toLowerCase();
        int n = Collections.binarySearch(keywords, prefix);
        if (n < 0 && -n <= keywords.size()) {
            String match = keywords.get(-n - 1);
            if (match.startsWith(prefix)) {
                // A completion is found
                String completion = match.substring(pos - w);
                // We cannot modify Document from within notification,
                // so we submit a task that does the change later
                SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
            }
        } else {
            // Nothing found
            mode = Mode.INSERT;
        }
    }

    /**
     *
     */
    public class CommitAction extends AbstractAction {

        /**
         *
         */
        private static final long serialVersionUID = 5794543109646743416L;

        @Override
        public void actionPerformed(ActionEvent ev) {
            if (mode == Mode.COMPLETION) {
                int pos = textField.getSelectionEnd();
                StringBuilder sb = new StringBuilder(textField.getText());
                sb.insert(pos, " ");
                textField.setText(sb.toString());
                textField.setCaretPosition(pos + 1);
                mode = Mode.INSERT;
            } else {
                textField.replaceSelection("\t");
            }
        }
    }

    private class CompletionTask implements Runnable {

        private final String completion;
        private final int position;

        CompletionTask(String completion, int position) {
            this.completion = completion;
            this.position = position;
        }

        @Override
        public void run() {
            StringBuilder sb = new StringBuilder(textField.getText());
            sb.insert(position, completion);
            textField.setText(sb.toString());
            textField.setCaretPosition(position + completion.length());
            textField.moveCaretPosition(position);
            mode = Mode.COMPLETION;
        }
    }

}
