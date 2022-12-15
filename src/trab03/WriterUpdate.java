package trab03;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static trab03.BookFrame.logger;

public class WriterUpdate extends javax.swing.JFrame {

    static Logger logger = Logger.getLogger(BookFrame.class.getName());
    static FileHandler fh;
    BookFrame bookframe;

    public void addBookRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) writerTable.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[7];

        ResultSet rs;
        String sql = "SELECT * FROM ESCRITOR";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {
                rowData[0] = rs.getInt(1);
                rowData[1] = rs.getString(2);
                rowData[2] = rs.getString(3);

                dtm.addRow(rowData);
            }

        }

    }

    public WriterUpdate(BookFrame bf) throws SQLException {
        initComponents();
        Connection con = new ConnectionFactory().establishConnection();
        this.bookframe = bf;
        addBookRows(con);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldWriterNome = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldWriterSobrenome = new javax.swing.JTextField();
        jButtonAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        writerTable = new javax.swing.JTable();
        jTextFieldWriterId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        jTextFieldWriterNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWriterNomeActionPerformed(evt);
            }
        });

        jLabel5.setText("Atualizar Livros");

        jLabel6.setText("Nome");

        jLabel7.setText("Sobrenome");

        jTextFieldWriterSobrenome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWriterSobrenomeActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        writerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nome", "Sobrenome"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        writerTable.setFocusable(false);
        writerTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                writerTableFocusGained(evt);
            }
        });
        writerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                writerTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(writerTable);

        jTextFieldWriterId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldWriterIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Id Escritor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldWriterId, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldWriterNome, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldWriterSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(151, 151, 151))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(170, 170, 170)
                        .addComponent(jButtonAtualizar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldWriterSobrenome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldWriterNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldWriterId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(170, 170, 170)
                .addComponent(jButtonAtualizar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldWriterNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWriterNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWriterNomeActionPerformed

    private void jTextFieldWriterSobrenomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWriterSobrenomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWriterSobrenomeActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        try {
            String id = jTextFieldWriterId.getText();
            String nome = jTextFieldWriterNome.getText();
            String sobrenome = jTextFieldWriterSobrenome.getText();
            if(id == null || id.isEmpty()){
                throw new NumberFormatException();
            }
            System.out.println(id);
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            //idEscritor = dao.getEscritorById(idEscritor);

            dao.updateEscritor(id, nome, sobrenome);
            DefaultTableModel dtm = (DefaultTableModel) writerTable.getModel();
            dtm.setRowCount(0);
            this.addBookRows(con);

            bookframe.clearEscritores();
            bookframe.addEscritorRows(con);

        } catch (SQLException ex) {

            logger.log(Level.SEVERE, null, "Escritor não Cadastrado");
        } catch (NumberFormatException ex) {
            Message m = new Message("Id Não Encontrado ");
            logger.log(Level.WARNING, null, ex);
        }


    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void writerTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_writerTableFocusGained

    }//GEN-LAST:event_writerTableFocusGained

    private void writerTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_writerTableMouseClicked

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);

            int index = writerTable.getSelectedRow();

            Object id = writerTable.getValueAt(index, 0);
            Object nome = writerTable.getValueAt(index, 1);
            Object sobrenome = writerTable.getValueAt(index, 2);

            jTextFieldWriterId.setText(id.toString());
            jTextFieldWriterNome.setText(nome.toString());
            jTextFieldWriterSobrenome.setText(sobrenome.toString());

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_writerTableMouseClicked

    private void jTextFieldWriterIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldWriterIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldWriterIdActionPerformed

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
            java.util.logging.Logger.getLogger(WriterUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WriterUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WriterUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WriterUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        try {
            fh = new FileHandler("BookUpdate.log", true);
        } catch (IOException ex) {
            Logger.getLogger(WriterUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(WriterUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.addHandler(fh);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldWriterId;
    private javax.swing.JTextField jTextFieldWriterNome;
    private javax.swing.JTextField jTextFieldWriterSobrenome;
    private javax.swing.JTable writerTable;
    // End of variables declaration//GEN-END:variables
}
