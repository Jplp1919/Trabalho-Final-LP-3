/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package trab03;

import org.junit.Test;
import static org.junit.Assert.*;

import trab03.PersistDAO;
import trab03.DadosEstoque;
import trab03.ConnectionFactory;
import trab03.Escritor;
import trab03.Livro;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import trab03.Editora;

/**
 *
 * @author Usuário
 */
public class PersistDAOTest {

    private Connection con;

    @Before
    public void setUp() throws SQLException {
        con = new ConnectionFactory().establishConnection();
        con.setAutoCommit(false);

    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }

    @Test
    public void deveCadastrarEscritor() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        List<String> teste = dao.getEscritorById(escritor.getId());
        assertNotNull(teste);
    }

    @Test
    public void deveCadastrarLivroEEncontrarPorTitulo() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        String titulo = "Teste 1";
        String genero = "Teste Genero";
        String isbn = "Teste Isbn";
        Double preco = 19.0;
        int idEscritor = escritor.getId();
        Livro livro = new Livro(titulo, genero, isbn, preco, idEscritor);
        dao.saveLivro(livro);
        Livro teste = dao.getLivroByTitulo("Teste 1");
        assertNotNull(teste);
    }

    @Test
    public void deveCadastrarInventorioERetornarQuantidadedeLivrosPorId() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        String titulo = "Teste inventorio";
        String genero = "Teste Genero";
        String isbn = "Teste Isbn";
        Double preco = 19.0;
        int idEscritor = escritor.getId();
        Livro livro = new Livro(titulo, genero, isbn, preco, idEscritor);
        dao.saveLivro(livro);
        int quantidade = 45;
        dao.updateEstoque(livro.getId(), quantidade);
        int teste = dao.getEstoquePorId(Integer.toString(livro.getId()));
        assertEquals(quantidade, teste);
    }

    
        @Test
    public void deveAtualizarQuantidadeNoInventorio() throws SQLException {
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        String titulo = "Teste inventorio";
        String genero = "Teste Genero";
        String isbn = "Teste Isbn";
        Double preco = 19.0;
        int idEscritor = escritor.getId();
        Livro livro = new Livro(titulo, genero, isbn, preco, idEscritor);
        dao.saveLivro(livro);
        int quantidade = 45;
        int quantidadeTeste = 65;
        //dao.saveEstoque(livro.getId(), quantidade);
        dao.updateEstoque(livro.getId(), quantidadeTeste);
        int teste = dao.getEstoquePorId(Integer.toString(livro.getId()));
        assertEquals(quantidadeTeste, teste);
    }
    
  @Test
  public void deverRetornarDadosDeEstoque() throws SQLException{
        int id = 9995;
        String nome = "Testward";
        String sobrenome = "Testworth";
        PersistDAO dao = new PersistDAO(con);
        Escritor escritor = new Escritor(id, nome, sobrenome);
        dao.saveEscritor(escritor);
        String titulo = "Teste inventorio";
        String genero = "Teste Genero";
        String isbn = "Teste Isbn";
        Double preco = 19.0;
        int idEscritor = escritor.getId();
        Livro livro = new Livro(titulo, genero, isbn, preco, idEscritor);
        dao.saveLivro(livro);
        List<DadosEstoque> teste = dao.getDadosEstoque();
        assertNotNull(teste);
  }
 
  @Test
  public void deveCadastrarEditora() throws SQLException{
      Editora editora = new Editora();
      editora.setNome("testenome");
      
      PersistDAO dao = new PersistDAO(con);
      dao.saveEditora(editora);
      int teste = dao.getEditoraPorNome(editora.getNome());
      assertNotNull(teste);
  }
  
    @Test
  public void deveDeletarUmaEditora() throws SQLException{
      Editora editora = new Editora();
      editora.setNome("testenome");
      editora.setIdEditora(998);
      PersistDAO dao = new PersistDAO(con);
      dao.saveEditoraId(editora);
      dao.deleteEditora(editora.getIdEditora());
      Editora teste = dao.getEditora(editora.getIdEditora());
      assertNull(teste);
  }
}