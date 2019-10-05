package inaki.sw.mines.view.swing;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.view.ChooseGameViewInterface;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_EASY;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_HARD;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_MEDIUM;
import static inaki.sw.mines.view.ChooseGameViewInterface.CGV_START;
import java.io.IOException;
import static java.lang.Math.min;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import static javax.swing.UIManager.setLookAndFeel;

/**
 *
 * @author inaki
 */
public class ChooseGameView extends javax.swing.JFrame implements ChooseGameViewInterface {

    /**
     * Creates new form ChooseGameView
     *
     * @throws java.io.IOException
     */
    public ChooseGameView() throws IOException {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("I-SW Mines");
        setAlwaysOnTop(true);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/isw-mines-96.png")).getImage());
        setResizable(false);

        jpDefault.setBorder(javax.swing.BorderFactory.createTitledBorder("Default Sizes"));

        jbEasy.setText("<html>\n<body style=\"text-align:center;color:green;\">\n<h1>8 x 8</h1>\n<h2>10 mines</h2>\n</body>\n </html>");
        jbEasy.setFocusable(false);

        jbMedium.setText("<html>\n<body style=\"text-align:center;color:blue;\">\n<h1>16 x 16</h1>\n<h2>40 mines</h2>\n</body>\n </html>");
        jbMedium.setFocusable(false);

        jbHard.setText("<html>\n<body style=\"text-align:center;color:red;\">\n<h1>30 x 16</h1>\n<h2>99 mines</h2>\n</body>\n </html>");
        jbHard.setFocusable(false);

        javax.swing.GroupLayout jpDefaultLayout = new javax.swing.GroupLayout(jpDefault);
        jpDefault.setLayout(jpDefaultLayout);
        jpDefaultLayout.setHorizontalGroup(
            jpDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDefaultLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbEasy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpDefaultLayout.setVerticalGroup(
            jpDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpDefaultLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpDefaultLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEasy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbMedium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jbHard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jpCustom.setBorder(javax.swing.BorderFactory.createTitledBorder("Custom Size"));

        jlHorizontal.setText("Horizontal:");
        jlHorizontal.setFocusable(false);

        jsHorizontal.setModel(new javax.swing.SpinnerNumberModel(10, 4, 30, 1));
        jsHorizontal.setFocusable(false);
        jsHorizontal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsHorizontalStateChanged(evt);
            }
        });

        jlVertical.setText("Vertical:");
        jlVertical.setFocusable(false);

        jsVertical.setModel(new javax.swing.SpinnerNumberModel(10, 4, 20, 1));
        jsVertical.setFocusable(false);
        jsVertical.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsVerticalStateChanged(evt);
            }
        });

        jlMines.setText("Mines:");
        jlMines.setFocusable(false);

        jsMines.setModel(new javax.swing.SpinnerNumberModel(10, 1, 999, 1));
        jsMines.setFocusable(false);
        jsMines.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jsMinesStateChanged(evt);
            }
        });

        jbStart.setText("Start");
        jbStart.setFocusable(false);

        javax.swing.GroupLayout jpCustomLayout = new javax.swing.GroupLayout(jpCustom);
        jpCustom.setLayout(jpCustomLayout);
        jpCustomLayout.setHorizontalGroup(
            jpCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCustomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlHorizontal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlVertical)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlMines)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsMines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpCustomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbStart)
                .addContainerGap())
        );
        jpCustomLayout.setVerticalGroup(
            jpCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpCustomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpCustomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlHorizontal)
                    .addComponent(jsHorizontal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlVertical)
                    .addComponent(jsVertical, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlMines)
                    .addComponent(jsMines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbStart)
                .addContainerGap())
        );

        jlTxtVersion.setText("Version:");

        jlNumVersion.setText("2.1");

        jbStatistics.setText("Statistics");
        jbStatistics.setFocusable(false);

        javax.swing.GroupLayout jpStatusLayout = new javax.swing.GroupLayout(jpStatus);
        jpStatus.setLayout(jpStatusLayout);
        jpStatusLayout.setHorizontalGroup(
            jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTxtVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jlNumVersion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbStatistics)
                .addContainerGap())
        );
        jpStatusLayout.setVerticalGroup(
            jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpStatusLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTxtVersion)
                    .addComponent(jlNumVersion)
                    .addComponent(jbStatistics))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpDefault, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpCustom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpDefault, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpCustom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jpStatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jsHorizontalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsHorizontalStateChanged
        jsMines.setValue(min(
                (int) jsMines.getValue(),
                (int) jsHorizontal.getValue() * (int) jsVertical.getValue() - 10
        )
        );
    }//GEN-LAST:event_jsHorizontalStateChanged

    private void jsVerticalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsVerticalStateChanged
        jsMines.setValue(min(
                (int) jsMines.getValue(),
                (int) jsHorizontal.getValue() * (int) jsVertical.getValue() - 10
        )
        );
    }//GEN-LAST:event_jsVerticalStateChanged

    private void jsMinesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jsMinesStateChanged
        jsMines.setValue(min(
                (int) jsMines.getValue(),
                (int) jsHorizontal.getValue() * (int) jsVertical.getValue() - 10
        )
        );
    }//GEN-LAST:event_jsMinesStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private final javax.swing.JButton jbEasy = new javax.swing.JButton();
    private final javax.swing.JButton jbHard = new javax.swing.JButton();
    private final javax.swing.JButton jbMedium = new javax.swing.JButton();
    private final javax.swing.JButton jbStart = new javax.swing.JButton();
    private final javax.swing.JButton jbStatistics = new javax.swing.JButton();
    private final javax.swing.JLabel jlHorizontal = new javax.swing.JLabel();
    private final javax.swing.JLabel jlMines = new javax.swing.JLabel();
    private final javax.swing.JLabel jlNumVersion = new javax.swing.JLabel();
    private final javax.swing.JLabel jlTxtVersion = new javax.swing.JLabel();
    private final javax.swing.JLabel jlVertical = new javax.swing.JLabel();
    private final javax.swing.JPanel jpCustom = new javax.swing.JPanel();
    private final javax.swing.JPanel jpDefault = new javax.swing.JPanel();
    private final javax.swing.JPanel jpStatus = new javax.swing.JPanel();
    private final javax.swing.JSpinner jsHorizontal = new javax.swing.JSpinner();
    private final javax.swing.JSpinner jsMines = new javax.swing.JSpinner();
    private final javax.swing.JSpinner jsVertical = new javax.swing.JSpinner();
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param c
     */
    @Override
    public void setController(Controller c) {
        jbEasy.addActionListener(c);
        jbEasy.setActionCommand(CGV_EASY);
        jbMedium.addActionListener(c);
        jbMedium.setActionCommand(CGV_MEDIUM);
        jbHard.addActionListener(c);
        jbHard.setActionCommand(CGV_HARD);
        jbStart.addActionListener(c);
        jbStart.setActionCommand(CGV_START);
        jbStatistics.addActionListener(c);
        jbStatistics.setActionCommand(CGV_STATISTICS);
    }

    @Override
    public void startView() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
        try {
            setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            updateComponentTreeUI(this);
            this.repaint();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            getLogger(ChooseGameView.class.getName()).log(SEVERE, null, ex);
        } finally {
            this.pack();
        }
        //</editor-fold>
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void hideView() {
        this.setVisible(false);
    }

    @Override
    public void enableStatistics(boolean b) {
        jbStatistics.setEnabled(b);
    }

    @Override
    public int getHorizontal() {
        return (int) jsHorizontal.getValue();
    }

    @Override
    public int getVertical() {
        return (int) jsVertical.getValue();
    }

    @Override
    public int getMines() {
        return (int) jsMines.getValue();
    }

}
