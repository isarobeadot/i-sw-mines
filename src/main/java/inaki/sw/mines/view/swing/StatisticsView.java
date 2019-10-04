package inaki.sw.mines.view.swing;

import inaki.sw.mines.controller.Controller;
import java.awt.BorderLayout;
import java.util.List;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.SwingUtilities.updateComponentTreeUI;
import static javax.swing.UIManager.setLookAndFeel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import inaki.sw.mines.view.StatisticsViewInterface;

/**
 *
 * @author inaki
 */
public class StatisticsView extends javax.swing.JFrame implements StatisticsViewInterface {

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

        jlTime1.setText("Elapsed time:");

        jlTime2.setText("0:0");

        javax.swing.GroupLayout jpTimeLayout = new javax.swing.GroupLayout(jpTime);
        jpTime.setLayout(jpTimeLayout);
        jpTimeLayout.setHorizontalGroup(
            jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTime1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlTime2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jpTimeLayout.setVerticalGroup(
            jpTimeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpTimeLayout.createSequentialGroup()
                .addContainerGap()
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
        jbOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbOKActionPerformed(evt);
            }
        });

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

    private void jbOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbOKActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jbOKActionPerformed

    @Override
    public void setController(Controller c) {
        jbSave.addActionListener(c);
        jbSave.setActionCommand(SV_SAVE);
    }

    @Override
    public void startView() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code">
        try {
            setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            updateComponentTreeUI(this);
            this.repaint();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            getLogger(MainView.class.getName()).log(SEVERE, null, ex);
        } finally {
            this.pack();
        }
        //</editor-fold>
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void hideView() {
    }

    @Override
    public void setTime(int m, int s) {
        String time = (m < 10 ? "0" : "") + m + ":" + (s < 10 ? "0" : "") + s;
        jlTime2.setText(time);
    }

    @Override
    public void setPrimaryClikNumber(int clikNo) {
        jlClickNo2.setText(clikNo + "");
    }

    @Override
    public void setRatio(double ratio) {
        final java.text.DecimalFormat df = new java.text.DecimalFormat("#.#");
        jlRatio3.setText(df.format(ratio) + "");
    }

    @Override
    public void setDiscoveredHistory(List<Integer> discoveredHistory) {
        // TODO: to do
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < discoveredHistory.size(); i++) {
            dataset.addValue(discoveredHistory.get(i), "", i + "");
        }

        JFreeChart lineChart = ChartFactory.createLineChart("Discovered percentage along time", "t (s)", "discovered %", dataset, PlotOrientation.VERTICAL, true, true, false);

        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setMouseWheelEnabled(true);
        chartPanel.setPreferredSize(new java.awt.Dimension(512, 300));
        jpChart.setLayout(new java.awt.BorderLayout());
        jpChart.add(chartPanel, BorderLayout.CENTER);
        jpChart.validate();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbOK;
    private javax.swing.JButton jbSave;
    private javax.swing.JLabel jlClickNo1;
    private javax.swing.JLabel jlClickNo2;
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
