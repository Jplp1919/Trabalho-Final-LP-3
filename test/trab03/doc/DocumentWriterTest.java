package trab03.doc;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import trab03.ConnectionFactory;
import trab03.Escritor;
import trab03.Livro;
import trab03.PersistDAO;

public class DocumentWriterTest {

    Connection con;
    DocumentWriter dw;

    public DocumentWriterTest() {
    }

    @Before
    public void setUp() throws SQLException {
        con = new ConnectionFactory().establishConnection();
        con.setAutoCommit(false);
        dw = new DocumentWriter(con);
    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }

    @Test
    public void deveEscreverEscritoresParaXml() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritorId(escritor);
        dw.writeEscritores("./testexml/escritores.xml");
        File f = new File("./testexml/escritores.xml");
        assertNotNull(f);
    }

    @Test
    public void deveEscreverLivrosParaXml() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritorId(escritor);
        String titulo = "Teste 1";
        String genero = "Teste Genero";
        String isbn = "Teste Isbn";
        Double preco = 19.0;
        int idEscritor = escritor.getId();
        Livro livro = new Livro(titulo, genero, isbn, preco, idEscritor);
        dao.saveLivro(livro);
        dw.writeLivros("./testexml/livros.xml");
        File f = new File("./testexml/escritores.xml");
        assertNotNull(f);
    }
    

}
