package trab03.doc;

import java.util.List;
import org.w3c.dom.Document;
import trab03.Escritor;
import trab03.PersistDAO;
import java.sql.Connection;
import java.sql.SQLException;
import org.w3c.dom.Element;
import trab03.Editora;
import trab03.Livro;

public class DocumentWriter {
    //  Document doc;

    Document doc = DocumentManipulatorXML.newDocument();
    Connection con;

    public DocumentWriter(Connection con) {
        this.con = con;
    }

    /**
     * <Editora>
     * <Id> ... </Id>
     * <Nome> ... </Nome>
     * </Editora>
     *
     */
    private void writeEditora(Editora editora, Element master) {
        PersistDAO dao = new PersistDAO(con);

        //<Escritor>
        Element root = doc.createElement("Editora");
        master.appendChild(root);

        // <Id>
        Element id = doc.createElement("Id");
        int ideditora = editora.getIdEditora();
        id.setTextContent(Integer.toString(ideditora));
        root.appendChild(id);

        String nomeeditora = editora.getNome();

        // <Nome>
        Element nome = doc.createElement("Nome");
        nome.setTextContent(nomeeditora);
        root.appendChild(nome);

    }

    public void writeEditoras(String path) throws SQLException {
        PersistDAO dao = new PersistDAO(con);
        List<Editora> listaEditoras = dao.listarEditoras();
        Editora editora;
        Element master = doc.createElement("Editoras");
        doc.appendChild(master);
        for (int i = 0; i < listaEditoras.size(); i++) {
            editora = listaEditoras.get(i);
            this.writeEditora(editora, master);

        }
        DocumentManipulatorXML.writeXmlFile(doc, path);
    }

    /**
     * <Escritor>
     * <Id> ... </Id>
     * <Nome> ... </Nome>
     * <Sobrenome> ... </Sobrenome>
     * </Escritor>
     *
     */
    private void writeEscritor(Escritor escritor, Element master) {
        // PersistDAO dao = new  PersistDAO(con);

        //<Escritor>
        Element root = doc.createElement("Escritor");
        master.appendChild(root);

        // <Id>
        Element id = doc.createElement("Id");
        id.setTextContent(Integer.toString(escritor.getId()));
        root.appendChild(id);

        // <Nome>
        Element nome = doc.createElement("Nome");
        nome.setTextContent(escritor.getPrimeiroNome());
        root.appendChild(nome);

        // <Sobrenome>
        Element sobrenome = doc.createElement("Sobrenome");
        sobrenome.setTextContent(escritor.getSobreNome());
        root.appendChild(sobrenome);

    }

    public void writeEscritores(String path) throws SQLException {
        PersistDAO dao = new PersistDAO(con);
        List<Escritor> listaEscritores = dao.listarEscritores();
        Escritor escritor;
        Element master = doc.createElement("Escritores");
        doc.appendChild(master);
        for (int i = 0; i < listaEscritores.size(); i++) {
            escritor = listaEscritores.get(i);
            this.writeEscritor(escritor, master);

        }
        DocumentManipulatorXML.writeXmlFile(doc, path);
    }

    /**
     * <Livro>
     * <Id> ... </Id>
     * <Titulo> ... </Titulo>
     * <Genero> ... </Genero>
     * <Isbn> ... </Isbn>
     * <Preco> ... </Preco>
     * <IdEscritor> ... </IdEscritor>
     * </Livro>
     *
     */
    private void writeLivro(Livro livro, Element master) {
        //<Escritor>
        Element root = doc.createElement("Livro");
        master.appendChild(root);

        // <Id>
        Element id = doc.createElement("Id");
        id.setTextContent(Integer.toString(livro.getId()));
        root.appendChild(id);

        // <Titulo>
        Element titulo = doc.createElement("Titulo");
        titulo.setTextContent(livro.getTitulo());
        root.appendChild(titulo);

        // <Genero>
        Element genero = doc.createElement("Genero");
        genero.setTextContent(livro.getGenero());
        root.appendChild(genero);

        //<Isbn>
        Element isbn = doc.createElement("Isbn");
        isbn.setTextContent(livro.getIsbn());
        root.appendChild(isbn);

        //<Preco>
        Element preco = doc.createElement("Preco");
        preco.setTextContent(Double.toString(livro.getPreco()));
        root.appendChild(preco);

        // <IdEscritor>
        Element idescritor = doc.createElement("IdEscritor");
        idescritor.setTextContent(Integer.toString(livro.getIdEscritor()));
        root.appendChild(idescritor);

    }

    public void writeLivros(String path) throws SQLException {
        PersistDAO dao = new PersistDAO(con);
        List<Livro> listaLivros = dao.listarLivros();
        Livro livro;
        Element master = doc.createElement("Livros");
        doc.appendChild(master);
        for (int i = 0; i < listaLivros.size(); i++) {
            livro = listaLivros.get(i);
            this.writeLivro(livro, master);

        }
        DocumentManipulatorXML.writeXmlFile(doc, path);
    }

}
