package inaki.sw.mines.view.swing;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.GameType;
import inaki.sw.mines.view.IStatisticsView;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class StatisticsView extends JFrame implements IStatisticsView {

    private boolean disableNimbus;

    /**
     * Creates new form StatisticsView
     */
    public StatisticsView() {
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

        jpWin = new javax.swing.JPanel();
        jlWin = new javax.swing.JLabel();
        jpTime = new javax.swing.JPanel();
        jlGameType1 = new javax.swing.JLabel();
        jlGameType2 = new javax.swing.JLabel();
        jlTime1 = new javax.swing.JLabel();
        jlTime2 = new javax.swing.JLabel();
        jpClicks = new javax.swing.JPanel();
        jlClickNo1 = new javax.swing.JLabel();
        jlClickNo2 = new javax.swing.JLabel();
        jlRatio1 = new javax.swing.JLabel();
        jlRatio2 = new javax.swing.JLabel();
        jlRatio3 = new javax.swing.JLabel();
        jlRatio4 = new javax.swing.JLabel();
        jpChart = new javax.swing.JPanel();
        jpBottom = new javax.swing.JPanel();
        jbOK = new javax.swing.JButton();
        jbSave = new javax.swing.JButton();

        setTitle("I-SW Mines");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/isw-mines-96.png")).getImage());

        jlWin.setText("<html><body><h2>Congratulations! You won the game!</h2></body></html>");

        javax.swing.GroupLayout jpWinLayout = new javax.swing.GroupLayout(jpWin);
        jpWin.setLayout(jpWinLayout);
        jpWinLayout.setHorizontalGroup(
            jpWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpWinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jlWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpWinLayout.setVerticalGroup(
            jpWinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpWinLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jpTime.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jlGameType1.setText("Game type:");

        jlGameType2.setText("type");

        jlTime1.setText("Elapsed time:");

        jlTime2.setText("0:0");

        javax.swing.GroupLayout jpTimeLayout = new javax.swing.GroupLayout(jpTime);
        jpTime.setLayout(jpTimeLayout);
        jpTimeLayout.setHorizontalGroup(
            jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpTimeLayout.createSequentialGroup()
                        .addComponent(jlTime1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlTime2))
                    .addGroup(jpTimeLayout.createSequentialGroup()
                        .addComponent(jlGameType1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlGameType2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTimeLayout.setVerticalGroup(
            jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlGameType1)
                    .addComponent(jlGameType2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlTime1)
                    .addComponent(jlTime2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpClicks.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jlClickNo1.setText("Number of clicks:");

        jlClickNo2.setText("0");

        jlRatio1.setText("Ratio:");

        jlRatio2.setText("Discovered");

        jlRatio3.setText("0");

        jlRatio4.setText("cells by each click");

        javax.swing.GroupLayout jpClicksLayout = new javax.swing.GroupLayout(jpClicks);
        jpClicks.setLayout(jpClicksLayout);
        jpClicksLayout.setHorizontalGroup(
            jpClicksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClicksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClicksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jpClicksLayout.createSequentialGroup()
                        .addComponent(jlClickNo1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlClickNo2))
                    .addGroup(jpClicksLayout.createSequentialGroup()
                        .addComponent(jlRatio1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jlRatio2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlRatio3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jlRatio4)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpClicksLayout.setVerticalGroup(
            jpClicksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpClicksLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpClicksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlClickNo1)
                    .addComponent(jlClickNo2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jpClicksLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlRatio1)
                    .addComponent(jlRatio2)
                    .addComponent(jlRatio3)
                    .addComponent(jlRatio4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jpChart.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        javax.swing.GroupLayout jpChartLayout = new javax.swing.GroupLayout(jpChart);
        jpChart.setLayout(jpChartLayout);
        jpChartLayout.setHorizontalGroup(
            jpChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpChartLayout.setVerticalGroup(
            jpChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jbOK.setText("OK");

        jbSave.setText("Save");
        jbSave.setFocusPainted(false);
        jbSave.setFocusable(false);

        javax.swing.GroupLayout jpBottomLayout = new javax.swing.GroupLayout(jpBottom);
        jpBottom.setLayout(jpBottomLayout);
        jpBottomLayout.setHorizontalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbOK)
                .addContainerGap())
        );
        jpBottomLayout.setVerticalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbOK)
                    .addComponent(jbSave))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpWin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpClicks, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jpBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jpWin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpClicks, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jpBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     *
     * @param c
     */
    @Override
    public void setController(Controller c) {
        jbSave.addActionListener(c);
        jbSave.setActionCommand(SV_SAVE);
        jbOK.addActionListener(c);
        jbOK.setActionCommand(SV_OK);
    }

    /**
     *
     */
    @Override
    public void startView() {
        /* Set the look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code ">
        if (!disableNimbus) {
            try {
                UIManager.setLookAndFeel(new javax.swing.plaf.nimbus.NimbusLookAndFeel());
                SwingUtilities.updateComponentTreeUI(this);
                this.repaint();
            }
            catch (UnsupportedLookAndFeelException ex) {
                getLogger(MainView.class.getName()).log(SEVERE, null, ex);
            }
            finally {
                this.pack();
            }
        }
        //</editor-fold>
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     *
     */
    @Override
    public void hideView() {
        this.setVisible(false);
    }

    /**
     *
     * @param m
     * @param s
     */
    @Override
    public void setTime(int m, int s) {
        String time = (m < 10 ? "0" : "") + m + ":" + (s < 10 ? "0" : "") + s;
        jlTime2.setText(time);
    }

    /**
     *
     * @param clikNo
     */
    @Override
    public void setPrimaryClikNumber(int clikNo) {
        jlClickNo2.setText(clikNo + "");
    }

    /**
     *
     * @param ratio
     */
    @Override
    public void setRatio(double ratio) {
        final DecimalFormat df = new DecimalFormat("#.#");
        jlRatio3.setText(df.format(ratio) + "");
    }

    /**
     *
     * @param discoveredHistory
     */
    @Override
    public void setDiscoveredHistory(List<Integer> discoveredHistory) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < discoveredHistory.size(); i++) {
            dataset.addValue(discoveredHistory.get(i), "", i + "");
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Discovered percentage along time", "t (s)", "discovered %", dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new Dimension(512, 300));
        jpChart.removeAll();
        jpChart.setLayout(new BorderLayout());
        jpChart.add(chartPanel, BorderLayout.CENTER);
        jpChart.validate();
    }

    /**
     *
     * @param type
     */
    @Override
    public void setGameType(GameType type) {
        jlGameType2.setText(type.name());
    }

    /**
     *
     * @param readOnly
     */
    @Override
    public void setReadOnly(boolean readOnly) {
        jpWin.setVisible(!readOnly);
        jbSave.setVisible(!readOnly);
    }

    /**
     *
     * @param version
     */
    @Override
    public void setVersion(String version) {
        // empty
    }

    /**
     *
     * @param disableNimbus
     */
    @Override
    public void disableNimbus(boolean disableNimbus) {
        this.disableNimbus = disableNimbus;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbOK;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlClickNo1;
    private javax.swing.JLabel jlClickNo2;
    private javax.swing.JLabel jlGameType1;
    private javax.swing.JLabel jlGameType2;
    private javax.swing.JLabel jlRatio1;
    private javax.swing.JLabel jlRatio2;
    private javax.swing.JLabel jlRatio3;
    private javax.swing.JLabel jlRatio4;
    private javax.swing.JLabel jlTime1;
    private javax.swing.JLabel jlTime2;
    private javax.swing.JLabel jlWin;
    private javax.swing.JPanel jpBottom;
    private javax.swing.JPanel jpChart;
    private javax.swing.JPanel jpClicks;
    private javax.swing.JPanel jpTime;
    private javax.swing.JPanel jpWin;
    // End of variables declaration//GEN-END:variables
}
