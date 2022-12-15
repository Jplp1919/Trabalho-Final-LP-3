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
import static trab03.BookFrame.logger;
import trab03.doc.DocumentWriter;

/**
 *
 * @author Usuário
 */
public class EditoraRegister extends javax.swing.JFrame {

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

    public EditoraRegister() throws SQLException {
        initComponents();
        Connection con = new ConnectionFactory().establishConnection();
        addEditoraRows(con);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonSalvarEditora = new javax.swing.JButton();
        jTextFieldNomeEditora = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEditora = new javax.swing.JTable();
        jButtonExportarXml = new javax.swing.JButton();

        jButtonSalvarEditora.setText("Salvar");
        jButtonSalvarEditora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalvarEditoraActionPerformed(evt);
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

        jButtonExportarXml.setText("Exportar Xml");
        jButtonExportarXml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportarXmlActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jTextFieldNomeEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(jButtonSalvarEditora)))
                .addContainerGap(43, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonExportarXml)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jTextFieldNomeEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSalvarEditora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(jButtonExportarXml)
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSalvarEditoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalvarEditoraActionPerformed
        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            String nome = jTextFieldNomeEditora.getText();
            Editora editora = new Editora();
            editora.setNome(nome);
            dao.saveEditora(editora);
            this.clearEditoras();
            this.addEditoraRows(con);
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jButtonSalvarEditoraActionPerformed

    private void jButtonExportarXmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportarXmlActionPerformed
     try {
            Connection con = new ConnectionFactory().establishConnection();
            DocumentWriter dw = new DocumentWriter(con);
            dw.writeEditoras("./editoras.xml");
            logger.info("Xml de Esditoras Exportado com Sucesso");
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
        
        
        
    }//GEN-LAST:event_jButtonExportarXmlActionPerformed

    private void jTableEditoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEditoraMouseClicked

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
            java.util.logging.Logger.getLogger(EditoraRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditoraRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditoraRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditoraRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new EditoraRegister().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(EditoraRegister.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExportarXml;
    private javax.swing.JButton jButtonSalvarEditora;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEditora;
    private javax.swing.JTextField jTextFieldNomeEditora;
    // End of variables declaration//GEN-END:variables
}
