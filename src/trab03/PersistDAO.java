package trab03;

import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Level;

public class PersistDAO {

    private Connection con;
    static java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PersistDAO.class.getName());
    static FileHandler fh;

    public PersistDAO(Connection con) {
        this.con = con;

    }

    public void saveEscritor(Escritor escritor) throws SQLException {

        String sql = "INSERT INTO ESCRITOR (PRIMEIRONOME, SOBRENOME) VALUES (?, ?);";

        try ( PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            //pstm.setString(1, Integer.toString(escritor.getId()));
            pstm.setString(1, escritor.getPrimeiroNome());
            pstm.setString(2, escritor.getSobreNome());
            pstm.execute();

            try ( ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    escritor.setId(rst.getInt(1));
                }
                logger.info("Escritor Salvo com Sucuesso");
            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);

            }
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }

    public void saveEscritorId(Escritor escritor) throws SQLException {

        String sql = "INSERT INTO ESCRITOR (IDESCRITOR, PRIMEIRONOME, SOBRENOME) VALUES (?, ?, ?);";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setString(1, Integer.toString(escritor.getId()));
            pstm.setString(2, escritor.getPrimeiroNome());
            pstm.setString(3, escritor.getSobreNome());
            pstm.execute();

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }

    public void saveEditora(Editora editora) throws SQLException {

        String sql = "INSERT INTO Editora (Nome) VALUE (?);";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setString(1, editora.getNome());

            pstm.execute();

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }

        public void saveEditoraId(Editora editora) throws SQLException {

        String sql = "INSERT INTO Editora (IDEDITORA, NOME) VALUE (?, ?);";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setString(1, Integer.toString(editora.getIdEditora()));
            pstm.setString(2, editora.getNome());

            pstm.execute();

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }
    
    public void saveLivro(Livro livro) throws SQLException {

        String sql = "INSERT INTO LIVROS (TITULO, GENERO, ISBN, PRECO, IDESCRITOR) VALUES (?, ?, ?, ?, ?);";

        try ( PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setString(1, livro.getTitulo());
            pstm.setString(2, livro.getGenero());
            pstm.setString(3, livro.getIsbn());
            pstm.setDouble(4, livro.getPreco());
            pstm.setInt(5, livro.getIdEscritor());
            pstm.execute();
            try ( ResultSet rst = pstm.getGeneratedKeys()) {
                while (rst.next()) {
                    livro.setId(rst.getInt(1));
                    this.saveEstoque(livro.getId(), 0);
                }
                logger.info("Livro Salvo com Sucesso");
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, null, ex);

            }
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public void deleteLivro(int id) throws SQLException {

        String sql = "DELETE FROM LIVROS WHERE IDLIVROS = ?";
        try ( PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setInt(1, id);
            pstm.execute();

            Integer listaModificadas = pstm.getUpdateCount();
            if (listaModificadas != 0) {
                logger.info("Livro(s) Deletado(s)");
                logger.log(Level.INFO, "Quantidade de linhas modificadas: {0}", listaModificadas);
            } else {
                logger.info("Livro(s) não Deletado(s)");
                // logger.log(Level.INFO, "Quantidade de linhas modificadas: {0}", listaModificadas); 
            }

        } catch (IOException | SecurityException | SQLException ex) {
            logger.log(Level.SEVERE, null, ex);

        }
    }

    public void deleteEscritor(int id) throws SQLException {

        String sql = "DELETE FROM Escritor WHERE IDESCRITOR = ?";
        try ( PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setInt(1, id);
            pstm.execute();

            Integer listaModificadas = pstm.getUpdateCount();
            if (listaModificadas != 0) {
                logger.info("Escritor(es) Deletado(s)");
                logger.log(Level.INFO, "Quantidade de linhas modificadas: {0}", listaModificadas);
            } else {
                logger.info("Escritor(es) não Deletado(s)");
            }

        } catch (IOException | SecurityException | SQLException ex) {
            Message m = new Message("Escritor com Livros Cadastrados");
            logger.log(Level.SEVERE, null, ex);

        }
    }

    public List<Livro> listarLivros() throws SQLException {
        List<Livro> livros = new ArrayList<>();

        String sql = "SELECT IDLIVROS, TITULO, GENERO, ISBN, PRECO, IDESCRITOR, IDEDITORA FROM LIVROS";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Livro livro
                            = new Livro(rs.getInt(1), rs.getString(2), rs.getString(3),
                                    rs.getString(4), rs.getDouble(5), rs.getInt(6),
                                    rs.getInt(7));

                    livros.add(livro);
                }
                logger.info("Livros Mostrados");
                logger.log(Level.INFO, "Quantidade de Livros: {0}", livros.size());
                return livros;

            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);
                return null;
            }
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Escritor> listarEscritores() throws SQLException {
        List<Escritor> escritores = new ArrayList<>();

        String sql = "SELECT * FROM ESCRITOR";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.execute();
            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Escritor escritor = new Escritor(rs.getInt(1), rs.getString(2), rs.getString(3));
                    escritores.add(escritor);
                }
                logger.info("Escritores Mostrados");
                logger.log(Level.INFO, "Quantidade de Escritores: {0}", escritores.size());
            }

        } catch (IOException | SecurityException | SQLException ex) {
            logger.log(Level.WARNING, null, ex);
            return null;
        }
        return escritores;
    }

        public List<Editora> listarEditoras() throws SQLException {
        List<Editora> editoras = new ArrayList<>();

        String sql = "SELECT * FROM EDITORA";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.execute();
            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Editora editora = new Editora(rs.getInt(1), rs.getString(2));
                    editoras.add(editora);
                }
                if(editoras.size() != 0){
                logger.info("Editoras Mostradas");
                logger.log(Level.INFO, "Quantidade de Editoras: {0}", editoras.size());
                } else {
                    logger.info("Nenhuma Editora Cadastrada");
                }

            }

        } catch (IOException | SecurityException | SQLException ex) {
            logger.log(Level.WARNING, null, ex);
            return null;
        }
        return editoras;
    }
    public List<Livro> buscarLivro(int i) throws SQLException {
        List<Livro> livros = new ArrayList<Livro>();

        String sql = "SELECT IDLIVROS, TITULO, GENERO, ISBN, PRECO, IDESCRITOR FROM LIVROS WHERE IDESCRITOR = ?";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setInt(1, i);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Livro livro
                            = new Livro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));

                    livros.add(livro);
                }
                logger.info("Livros Buscados");
                logger.log(Level.INFO, "Quantidade de Livros: {0}", livros.size());
                return livros;
            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);
                return null;
            }
        } catch (IOException | SecurityException ex) {
            java.util.logging.Logger.getLogger(PersistDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void updateLivro(String id, String titulo, String genero, String isbn, String preco, String idEscritor, String nomeEditora) throws SQLException {
        try {
            String sql;
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            if (!titulo.equals("")) {
                sql = "UPDATE LIVROS SET TITULO = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {

                    ps.setString(1, titulo);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Titulo Atualizado no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }
            if (!genero.equals("")) {
                sql = "UPDATE LIVROS SET GENERO = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, genero);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Genero Atualizado no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }
            if (!preco.equals("")) {
                sql = "UPDATE LIVROS SET PRECO = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, preco);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Preco Atualizado no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }
            if (!isbn.equals("")) {
                sql = "UPDATE LIVROS SET ISBN = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, isbn);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Isbn Atualizado no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }

            }

            if (!idEscritor.equals("")) {
                sql = "UPDATE LIVROS SET IDESCRITOR = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, idEscritor);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Id do Escritor Atualizado no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }

            }
            if (!nomeEditora.equals("")) {
                PersistDAO dao = new PersistDAO(con);
                String idEditora = Integer.toString(dao.getEditoraPorNome(nomeEditora));

                sql = "UPDATE LIVROS SET IDEDITORA = ? WHERE IDLIVROS = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, idEditora);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Id da Editora Atualizada no Livro com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    public void updateEscritor(String id, String nome, String sobrenome) throws SQLException {
        try {
            String sql;
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            if (!nome.equals("")) {
                sql = "UPDATE ESCRITOR SET PRIMEIRONOME = ? WHERE IDESCRITOR = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, nome);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Primeiro Nome Atualizado no Escritor com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }

            if (!sobrenome.equals("")) {
                sql = "UPDATE ESCRITOR SET SOBRENOME = ? WHERE IDESCRITOR = ?";
                try ( PreparedStatement ps = con.prepareStatement(sql)) {
                    ps.setString(1, sobrenome);
                    ps.setString(2, id);
                    ps.execute();
                    logger.log(Level.INFO, "Sobrenome Atualizado no Escritor com Id: {0}", id);
                } catch (SQLException ex) {
                    logger.log(Level.WARNING, null, ex);

                }
            }
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    public void cleanTableBook() throws SQLException {
        try {
            String sql = "DELETE FROM LIVROS";
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.execute();
                logger.warning("Livros Deletados");
            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);

            }

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }

    public void cleanTableWriter() throws SQLException {
        try {
            String sql = "DELETE FROM ESCRITOR";
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.execute();
                logger.warning("Escritores Deletados");
            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);

            }

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }

    }

    public int getEscritorByNome(String nome, String sobrenome) throws SQLException {
        String sql = "SELECT IDESCRITOR FROM ESCRITOR WHERE PRIMEIRONOME LIKE ? AND SOBRENOME LIKE ?";
        int id = 0;

        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nome);
            ps.setString(2, sobrenome);
            ps.execute();
            System.out.println(nome + " " + sobrenome);
            try ( ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {

                    id = rs.getInt(1);
                    System.out.println("ID = " + id);
                }
                return id;
            }
        }
    }

    public List<String> getEscritorById(int id) throws SQLException {
        String sql = "SELECT PRIMEIRONOME, SOBRENOME FROM ESCRITOR WHERE IDESCRITOR LIKE ? ";
        String nome;
        String sobrenome;
        String nomeCompleto = null;
        List<String> listanomes = new ArrayList<>();
        try ( PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);

            ps.execute();
            try ( ResultSet rs = ps.getResultSet()) {
                if (rs.next()) {

                    nome = rs.getString(1);
                    sobrenome = rs.getString(2);
                    nomeCompleto = nome + " " + sobrenome;
                    listanomes.add(nomeCompleto);
                    listanomes.add(nome);
                    listanomes.add(sobrenome);
                }
                return listanomes;
            }
        }
    }

    public Livro getLivroByTitulo(String nome) throws SQLException {
        String sql = "SELECT * FROM LIVROS WHERE TITULO LIKE ?";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Livro livro
                            = new Livro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDouble(5), rs.getInt(6));

                    return livro;
                }
                return null;
            }
        }

    }

    public int getEditoraPorNome(String nome) {
        String sql = "SELECT IDEDITORA FROM EDITORA WHERE NOME LIKE ?";
        int id = 0;
        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, nome);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    id = rs.getInt(1);

                    return id;
                }

            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return id;
    }
    public Editora getEditora (int id) {
        String sql = "SELECT IDEDITORA FROM EDITORA WHERE IDEDITORA LIKE ?";
        
        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    Editora editora = new Editora(rs.getInt(1), rs.getString(2));
                    

                    return editora;
                }

            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public int getEstoquePorId(String id) {
        String sql = "SELECT QUANTIDADE FROM INVENTORIO WHERE IDLIVROS = ?";
        int quantidade = 0;
        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, id);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    quantidade = rs.getInt(1);

                    return quantidade;
                }

            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return quantidade;
    }

    public void saveEstoque(int id, int quantidade) {
        //System.out.println(id + " " + quantidade);
        String sql = "INSERT INTO INVENTORIO (IDLIVROS, QUANTIDADE) VALUES( ? , ? );";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, id);
            pstm.setInt(2, quantidade);
            pstm.execute();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

    }

    public void updateEstoque(int id, int quantidade) {
        String sql = "UPDATE INVENTORIO SET QUANTIDADE = ? WHERE IDLIVROS = ?";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setInt(1, quantidade);
            pstm.setInt(2, id);
            pstm.execute();

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }

    public List<DadosEstoque> getDadosEstoque() throws SQLException {
        List<DadosEstoque> dadosLista = new ArrayList<>();

        String sql = "SELECT TITULO, PRECO, IDESCRITOR, IDEDITORA FROM LIVROS";

        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    DadosEstoque dados
                            = new DadosEstoque(rs.getString(1), rs.getDouble(2), rs.getInt(3), rs.getInt(4));

                    dadosLista.add(dados);

                }
                logger.info("Dados do Estoque Mostrados");

                return dadosLista;

            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);
                return null;
            }
        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String getEditoraPorId(String id) {
        String sql = "SELECT NOME FROM EDITORA WHERE IDEDITORA = ?";
        String editora = null;
        try ( PreparedStatement pstm = con.prepareStatement(sql)) {
            pstm.setString(1, id);
            pstm.execute();

            try ( ResultSet rs = pstm.getResultSet()) {
                while (rs.next()) {
                    editora = rs.getString(1);

                    return editora;
                }

            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
        }

        return editora;
    }
    
    public void wipeEditoras(){
        try {
            String sql = "DELETE FROM EDITORA";
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            try ( PreparedStatement ps = con.prepareStatement(sql)) {
                ps.execute();
                logger.warning("Editoras Deletadas");
            } catch (SQLException ex) {
                logger.log(Level.WARNING, null, ex);

            }

        } catch (IOException | SecurityException ex) {
            logger.log(Level.SEVERE, null, ex);

        }
    }

    public void deleteEditora(int id) {
            String sql = "DELETE FROM EDITORA WHERE IDEDITORA = ?";
        try ( PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fh = new FileHandler("PersistDAO.log", true);
            logger.addHandler(fh);
            pstm.setInt(1, id);
            pstm.execute();

            Integer listaModificadas = pstm.getUpdateCount();
            if (listaModificadas != 0) {
                logger.info("Editora(s) Deletada(s)");
                logger.log(Level.INFO, "Quantidade de linhas modificadas: {0}", listaModificadas);
            } else {
                logger.info("Editora(s) não Deletada(s)");
                // logger.log(Level.INFO, "Quantidade de linhas modificadas: {0}", listaModificadas); 
            }

        } catch (IOException | SecurityException | SQLException ex) {
            Message m = new Message("Editora com Livros Cadastrados");
            logger.log(Level.SEVERE, null, ex);

        }   
    
    }
}
