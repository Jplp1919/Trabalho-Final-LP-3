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

public class BookUpdate extends javax.swing.JFrame {

    static Logger logger = Logger.getLogger(BookFrame.class.getName());
    static FileHandler fh;
    BookFrame bookframe;

    public void addBookRows(Connection con) throws SQLException {
        DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
        PersistDAO dao = new PersistDAO(con);

        Object[] rowData = new Object[8];

        ResultSet rs;
        String sql = "SELECT IDLIVROS, TITULO, GENERO, ISBN, PRECO, IDESCRITOR, IDEDITORA FROM LIVROS ORDER BY TITULO ASC";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.execute();
            rs = pstm.getResultSet();

            while (rs.next()) {

                rowData[0] = rs.getInt(1);
                rowData[1] = rs.getString(2);
                rowData[2] = rs.getString(3);
                rowData[3] = rs.getString(4);
                rowData[4] = rs.getDouble(5);
                int idescritor = rs.getInt(6);
                List<String> nome = dao.getEscritorById(idescritor);
                rowData[5] = nome.get(0);
                rowData[6] = idescritor;

                rowData[7] = dao.getEditoraPorId(Integer.toString(rs.getInt(7)));
                dtm.addRow(rowData);
            }

        }

    }

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

    public BookUpdate(BookFrame bf) throws SQLException {
        initComponents();
        Connection con = new ConnectionFactory().establishConnection();
        this.bookframe = bf;
        addBookRows(con);
        addEditoraRows(con);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldBookTitulo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldBookGenero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldPreco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldIsbn = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldEscritorID = new javax.swing.JTextField();
        jButtonAtualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jTextFieldBookId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldEditora = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableEditora = new javax.swing.JTable();

        jTextFieldBookTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBookTituloActionPerformed(evt);
            }
        });

        jLabel5.setText("Atualizar Livros");

        jLabel6.setText("Título");

        jLabel7.setText("Genero");

        jTextFieldBookGenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBookGeneroActionPerformed(evt);
            }
        });

        jLabel9.setText("Preço");

        jTextFieldPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecoActionPerformed(evt);
            }
        });

        jLabel8.setText("Isbn");

        jTextFieldIsbn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIsbnActionPerformed(evt);
            }
        });

        jLabel10.setText("Id do Escritor");

        jTextFieldEscritorID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldEscritorIDActionPerformed(evt);
            }
        });

        jButtonAtualizar.setText("Atualizar");
        jButtonAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizarActionPerformed(evt);
            }
        });

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Titulo", "Genero", "ISBN", "Valor", "Escritor", "Id Escritor", "Editora"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bookTable.setFocusable(false);
        bookTable.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bookTableFocusGained(evt);
            }
        });
        bookTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookTable);

        jTextFieldBookId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBookIdActionPerformed(evt);
            }
        });

        jLabel3.setText("Id Livro");

        jLabel4.setText("Editoras");

        jLabel2.setText(" Editora");

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
        jScrollPane2.setViewportView(jTableEditora);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jLabel5))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jTextFieldBookId, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldBookTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldBookGenero, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel10)
                                    .addComponent(jTextFieldEscritorID, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(397, 397, 397)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextFieldEditora, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAtualizar))))
                        .addGap(0, 297, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(291, 291, 291)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3)
                    .addComponent(jLabel10))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldBookId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBookTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldIsbn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldBookGenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldEscritorID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldEditora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonAtualizar)
                .addGap(11, 11, 11))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldBookTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBookTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBookTituloActionPerformed

    private void jTextFieldBookGeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBookGeneroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBookGeneroActionPerformed

    private void jTextFieldPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecoActionPerformed

    private void jTextFieldEscritorIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldEscritorIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldEscritorIDActionPerformed

    private void jTextFieldIsbnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIsbnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIsbnActionPerformed

    private void jButtonAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizarActionPerformed
        
        try {
            String id = jTextFieldBookId.getText();
            String titulo = jTextFieldBookTitulo.getText();
            String genero = jTextFieldBookGenero.getText();
            String isbn = jTextFieldIsbn.getText();
            String preco = jTextFieldPreco.getText();
            int idEscritor = Integer.parseInt(jTextFieldEscritorID.getText());

            String NomeEditora = jTextFieldEditora.getText();

            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);
            //idEscritor = dao.getEscritorById(idEscritor);

            String idEscritorStr = Integer.toString(idEscritor);
            dao.updateLivro(id, titulo, genero, isbn, preco, idEscritorStr, NomeEditora);
            DefaultTableModel dtm = (DefaultTableModel) bookTable.getModel();
            dtm.setRowCount(0);
            this.addBookRows(con);
            logger.info("Livro Atualizado");
            bookframe.clearBooks();
            bookframe.addBookRows(con);
            bookframe.clearEsotque();
            bookframe.addEstoqueRows(con);

        } catch (SQLException ex) {

            logger.log(Level.SEVERE, null, "Escritor não Cadastrado");
        } catch (NumberFormatException ex) {
            Message m = new Message("Id Não Encontrado ");
            logger.log(Level.WARNING, null, ex);
        }


    }//GEN-LAST:event_jButtonAtualizarActionPerformed

    private void bookTableFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_bookTableFocusGained

    }//GEN-LAST:event_bookTableFocusGained

    private void bookTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bookTableMouseClicked

        try {
            Connection con = new ConnectionFactory().establishConnection();
            PersistDAO dao = new PersistDAO(con);

            int index = bookTable.getSelectedRow();

            Object id = bookTable.getValueAt(index, 0);
            Object titulo = bookTable.getValueAt(index, 1);
            Object genero = bookTable.getValueAt(index, 2);
            Object isbn = bookTable.getValueAt(index, 3);
            Object valor = bookTable.getValueAt(index, 4);
            String idEscritor = bookTable.getValueAt(index, 6).toString();

            jTextFieldBookId.setText(id.toString());
            jTextFieldBookTitulo.setText(titulo.toString());
            jTextFieldBookGenero.setText(genero.toString());
            jTextFieldIsbn.setText(isbn.toString());
            jTextFieldPreco.setText(valor.toString());

            jTextFieldEscritorID.setText(idEscritor);

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_bookTableMouseClicked

    private void jTextFieldBookIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBookIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBookIdActionPerformed

    private void jTableEditoraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableEditoraMouseClicked

        int index = jTableEditora.getSelectedRow();

        Object editora = jTableEditora.getValueAt(index, 1);

        jTextFieldEditora.setText(editora.toString());


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
            java.util.logging.Logger.getLogger(BookUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BookUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BookUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BookUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        try {
            fh = new FileHandler("BookUpdate.log", true);
        } catch (IOException ex) {
            Logger.getLogger(BookUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(BookUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        logger.addHandler(fh);

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookTable;
    private javax.swing.JButton jButtonAtualizar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTableEditora;
    private javax.swing.JTextField jTextFieldBookGenero;
    private javax.swing.JTextField jTextFieldBookId;
    private javax.swing.JTextField jTextFieldBookTitulo;
    private javax.swing.JTextField jTextFieldEditora;
    private javax.swing.JTextField jTextFieldEscritorID;
    private javax.swing.JTextField jTextFieldIsbn;
    private javax.swing.JTextField jTextFieldPreco;
    // End of variables declaration//GEN-END:variables
}
