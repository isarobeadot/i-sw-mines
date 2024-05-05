package inaki.sw.mines.view.swing;

import inaki.sw.mines.controller.Controller;
import inaki.sw.mines.model.GameType;
import inaki.sw.mines.model.StatisticList;
import inaki.sw.mines.view.IStatisticHistoryView;
import java.awt.event.ActionEvent;
import java.util.Date;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author inaki
 * @since 2.1 or before
 */
public class StatisticHistoryView extends JFrame implements IStatisticHistoryView {

    String[] columnNames = {"Date", "Name", "Time"};
    String[] columnNamesCustom = {"Date", "Name", "Time", "Size", "Mines"};
    Class[] types = new Class[]{
        Date.class, String.class, String.class
    };
    Class[] typesCustom = new Class[]{
        Date.class, String.class, String.class, String.class, Long.class
    };
    private Controller c;
    private int eventNo = 0;
    private boolean disableNimbus;

    /**
     * Creates new form StatisticHistoryView
     */
    public StatisticHistoryView() {
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

        jtpMain = new javax.swing.JTabbedPane();
        jspEasy = new javax.swing.JScrollPane();
        jtEasy = new javax.swing.JTable();
        jspMedium = new javax.swing.JScrollPane();
        jtMedium = new javax.swing.JTable();
        jspHard = new javax.swing.JScrollPane();
        jtHard = new javax.swing.JTable();
        jspCustom = new javax.swing.JScrollPane();
        jtCustom = new javax.swing.JTable();
        jpBottom = new javax.swing.JPanel();
        jbOK = new javax.swing.JButton();

        setTitle("I-SW Mines");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/icon/isw-mines-96.png")).getImage());

        jtpMain.setFocusable(false);

        jspEasy.setViewportView(jtEasy);

        jtpMain.addTab("Easy", jspEasy);

        jspMedium.setViewportView(jtMedium);

        jtpMain.addTab("Medium", jspMedium);

        jspHard.setViewportView(jtHard);

        jtpMain.addTab("Hard", jspHard);

        jspCustom.setViewportView(jtCustom);

        jtpMain.addTab("Custom", jspCustom);

        jbOK.setText("OK");

        javax.swing.GroupLayout jpBottomLayout = new javax.swing.GroupLayout(jpBottom);
        jpBottom.setLayout(jpBottomLayout);
        jpBottomLayout.setHorizontalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbOK)
                .addContainerGap())
        );
        jpBottomLayout.setVerticalGroup(
            jpBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpBottomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbOK)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jtpMain, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addComponent(jpBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jtpMain, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
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
        this.c = c;
        jbOK.addActionListener(c);
        jbOK.setActionCommand(SHV_OK);
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
                getLogger(ChooseGameView.class.getName()).log(SEVERE, null, ex);
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
     * @param set
     */
    @Override
    public void setStatistics(StatisticList set) {
        Object[][] easy = filterAndPrepareData(set, GameType.EASY);
        Object[][] medium = filterAndPrepareData(set, GameType.MEDIUM);
        Object[][] hard = filterAndPrepareData(set, GameType.HARD);
        Object[][] custom = set.stream().filter(s -> s.getType().equals(GameType.CUSTOM)).map(s -> {
            Object[] o = {s.getWinDate(), s.getName(), s.getTotalMinutes() + ":" + s.getTotalSeconds(), s.getHorizontalSize() + "x" + s.getVerticalSize(), s.getMineNumber()};
            return o;
        }).toArray(Object[][]::new);

        configureTable(jtEasy, easy, columnNames, types);
        configureTable(jtMedium, medium, columnNames, types);
        configureTable(jtHard, hard, columnNames, types);
        configureTable(jtCustom, custom, columnNamesCustom, typesCustom);
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

    private Object[][] filterAndPrepareData(StatisticList set, GameType type) {
        return set.stream().sorted((s1, s2) -> (Integer.valueOf(s1.getTotalMinutes() * 60 + s1.getTotalSeconds())).compareTo(s2.getTotalMinutes() * 60 + s2.getTotalSeconds())).filter(s -> s.getType().equals(type)).map(s -> {
            Object[] o = {s.getWinDate(), s.getName(), s.getTotalMinutes() + ":" + (s.getTotalSeconds() < 10 ? "0" : "") + s.getTotalSeconds()};
            return o;
        }).toArray(Object[][]::new);
    }

    private void configureTable(JTable table, Object[][] data, String[] columns, Class[] defaultTypes) {
        table.setModel(new DefaultTableModel(data, columns) {
            @Override
            public Class getColumnClass(int columnIndex) {
                return defaultTypes[columnIndex];
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }
        });
        ListSelectionModel cellSelectionModel = table.getSelectionModel();
        cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cellSelectionModel.addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                String tab;
                switch (jtpMain.getSelectedIndex()) {
                    case 0:
                        tab = SHV_EASY;
                        break;
                    case 1:
                        tab = SHV_MEDIUM;
                        break;
                    case 2:
                        tab = SHV_HARD;
                        break;
                    case 3:
                        tab = SHV_CUSTOM;
                        break;
                    default:
                        tab = SHV_NONE;
                }
                c.actionPerformed(new ActionEvent(this, eventNo, SHV_DETAIL + "," + tab + "," + ((Date) table.getValueAt(row, 0)).getTime()));
                eventNo++;
            }
            table.clearSelection();
        }
        );
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jbOK;
    private javax.swing.JPanel jpBottom;
    private javax.swing.JScrollPane jspCustom;
    private javax.swing.JScrollPane jspEasy;
    private javax.swing.JScrollPane jspHard;
    private javax.swing.JScrollPane jspMedium;
    private javax.swing.JTable jtCustom;
    private javax.swing.JTable jtEasy;
    private javax.swing.JTable jtHard;
    private javax.swing.JTable jtMedium;
    private javax.swing.JTabbedPane jtpMain;
    // End of variables declaration//GEN-END:variables
}
