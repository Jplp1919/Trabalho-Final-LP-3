/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package trab03;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuário
 */
public class EditoraDelete extends javax.swing.JFrame {

    public void addEditoraRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) jTableEditora.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[2];
        ResultSet rs;
        String sql = "SELECT * FROM EDITORA";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                rowData[0] = rs.getInt(1);
                rowData[1] = rs.getString(2);
                dtm.addRow(rowData);
            }

        }
    }

    public void clearEditoras() {
        DefaultTableModel dtm = (DefaultTableModel) jTableEditora.getModel();
        dtm.setRowCount(0);
    }

    public EditoraDelete() throws SQLException {
        initComponents();
        Connection con = new ConnectionFactory().establishConnection();
        addEditoraRows(con);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonDeletarEditora = new javax.swing.JButton();
        jTextFieldIdEditora = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEditora = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        jButtonDeletarEditora.setText("Deletar");
        jButtonDeletarEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeletarEditoraActionPerformed(evt);
            }
        });

        jTextFieldIdEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdEditoraActionPerformed(evt);
            }
        });

        jTableEditora.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID ", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableEditora.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableEditoraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableEditora);
        if (jTableEditora.getColumnModel().getColumnCount() > 0) {
            jTableEditora.getColumnModel().getColumn(0).setResizable(false);
            jTableEditora.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel1.setText("ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButtonDeletarEditora))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(155, 155, 155)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldIdEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldIdEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDeletarEditora)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeletarEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeletarEditoraActionPerformed
        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            String id = jTextFieldIdEditora.getText();

            dao.deleteEditora(Integer.parseInt(id));
            this.clearEditoras();
            this.addEditoraRows(con);
        } catch (SQLException ex) {
            Logger.getLogger(EditoraDelete.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
          Message m = new Message("Id Vazio");
        }


    }//GEN-LAST:event_jButtonDeletarEditoraActionPerformed

    private void jTextFieldIdEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdEditoraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdEditoraActionPerformed

    private void jTableEditoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEditoraMouseClicked
        int index = jTableEditora.getSelectedRow();

        Object id = jTableEditora.getValueAt(index, 0);

        jTextFieldIdEditora.setText(id.toString());

    }//GEN-LAST:event_jTableEditoraMouseClicked

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
            java.util.logging.Logger.getLogger(EditoraDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditoraDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditoraDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditoraDelete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EditoraDelete().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EditoraDelete.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDeletarEditora;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEditora;
    private javax.swing.JTextField jTextFieldIdEditora;
    // End of variables declaration//GEN-END:variables
}
