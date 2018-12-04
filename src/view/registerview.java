/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import DAO.RegistrationDAO;
import antlr.StringUtils;
import dto.RegistrationDTO;
import java.awt.Component;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author duy
 */
public class registerview extends javax.swing.JFrame {

    /**
     * Creates new form registerview
     */
    private Calendar monday;
    private DefaultTableModel model;
    private Date sunday;
    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    public registerview() {
        initComponents();
        init();
        initTable();
        resizeColumnWidth(jTable1);
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    private void initTable() {
        RegistrationDAO dao = new RegistrationDAO();
        List<RegistrationDTO> registrationDTOs = dao.getAllRegisterNextWeek(monday.getTime(), sunday);
        for (int i = 0; i < 7; i++) {
            monday.add(Calendar.DATE, i);
            Object[] object = new Object[3];
            String name1 = "";
            String name2 = "";
            sdf.setTimeZone(monday.getTimeZone());
            object[0] = sdf.format(monday.getTime());
            for (RegistrationDTO dto : registrationDTOs) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dto.getDate());
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                if (day == monday.get(Calendar.DAY_OF_MONTH) && month == monday.get(Calendar.MONTH)
                        && year == monday.get(Calendar.YEAR)) {
                    if (dto.getShiftId() == 1) {
                        name1 += dto.getName() + " ";
                    } else {
                        name2 += dto.getName() + " ";
                    }
                }
            }
            object[1] = name1;
            object[2] = name2;
            model.addRow(object);
            monday.add(Calendar.DATE, -i);
        }
    }

    private void init() {
//        LocalDate localDate = LocalDate.now();
//        DayOfWeek day = localDate.getDayOfWeek();
        monday = Calendar.getInstance();
        Date now = new Date();
        monday.setTime(now);
//        cal.add(Calendar.DATE, 1);
        int day = monday.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            case 2:
                monday.add(Calendar.DATE, 7);
                break;
            case 3:
                monday.add(Calendar.DATE, 6);
                break;
            case 4:
                monday.add(Calendar.DATE, 5);
                break;
            case 5:
                monday.add(Calendar.DATE, 4);
                break;
            case 6:
                monday.add(Calendar.DATE, 3);
                break;
            case 7:
                monday.add(Calendar.DATE, 2);
                break;
            case 1:
                monday.add(Calendar.DATE, 1);
                break;

        }
        monday.set(Calendar.HOUR_OF_DAY, 00);
        monday.set(Calendar.MINUTE, 00);
        monday.set(Calendar.SECOND, 00);
        model = (DefaultTableModel) jTable1.getModel();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("The date is: " + sdf.format(monday.getTime()));
        monday.add(Calendar.DATE, 6);
        sunday = monday.getTime();
        monday.add(Calendar.DATE, -6);

//        System.out.println(day+ monday.getTime());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Day", "Shift 1", "Shift 2"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Print Schedule");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(271, 271, 271)
                .addComponent(jButton1)
                .addGap(43, 43, 43)
                .addComponent(jButton2)
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(69, 69, 69))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        JTable source = (JTable) evt.getSource();
        int row = source.rowAtPoint(evt.getPoint());
        int column = source.columnAtPoint(evt.getPoint());
        String data = source.getModel().getValueAt(row, 0) + "";
        DateFormat format = new SimpleDateFormat("MM-dd-yyyy", Locale.ENGLISH);
        try {
            if (column == 1 || column == 2) {

                Date date = format.parse(data);
                listUser view = new listUser(monday.getTime(), column, date);
                view.setVisible(Boolean.TRUE);
                this.setVisible(Boolean.FALSE);
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            Logger.getLogger(registerview.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        homeview home = new homeview();
        home.setVisible(Boolean.TRUE);
        this.setVisible(Boolean.FALSE);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Are you want Print Schedule ?", "Confirm", dialogButton);
        if (dialogResult == 0) {
            choose() ;
        }
   

    }//GEN-LAST:event_jButton1ActionPerformed

    private void choose() {
        JFileChooser fc = new JFileChooser();
        int option = fc.showSaveDialog(registerview.this);
        if (option == JFileChooser.APPROVE_OPTION) {
            String filename = fc.getSelectedFile().getName();
            String path = fc.getSelectedFile().getParentFile().getPath();

            int len = filename.length();
            String ext = "";
            String file = "";

            if (len > 4) {
                ext = filename.substring(len - 4, len);
            }

            if (ext.equals(".xls")) {
                file = path + "/" + filename;
            } else {
                file = path + "/" + filename + ".xls";
            }

            File demo = new File(file);
            try {
                TableModel model = jTable1.getModel();
                FileWriter excel = new FileWriter(demo);

                for (int i = 0; i < model.getColumnCount(); i++) {
                    excel.write(model.getColumnName(i) + "\t");
                }

                excel.write("\n");

                for (int i = 0; i < model.getRowCount(); i++) {
                    for (int j = 0; j < model.getColumnCount(); j++) {
                        excel.write(model.getValueAt(i, j).toString() + "\t");
                    }
                    excel.write("\n");
                }

                excel.close();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * @param table
     * @param file
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(registerview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(registerview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(registerview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(registerview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new registerview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
