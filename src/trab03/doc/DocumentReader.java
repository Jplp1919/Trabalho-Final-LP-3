package trab03.doc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import trab03.Editora;
import trab03.Escritor;
import trab03.Livro;

public class DocumentReader {

    public List<Escritor> readEscritores(String path) throws XPathExpressionException, SAXException, ParserConfigurationException, IOException {
        File file = new File(path);
        List<Escritor> escritores = new ArrayList<>();

        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        doc.getDocumentElement().normalize();
        doc.getDocumentElement().getNodeName();
        NodeList nodeList
                = doc.getElementsByTagName("Escritor");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Escritor escritor = new Escritor();
            Node node = nodeList.item(i);
            node.getNodeName();
            if (node.getNodeType()
                    == Node.ELEMENT_NODE) {
                Element tElement = (Element) node;
                String id
                        = tElement
                                .getElementsByTagName("Id")
                                .item(0)
                                .getTextContent();
                String nome = tElement
                        .getElementsByTagName(
                                "Nome")
                        .item(0)
                        .getTextContent();
                String sobrenome = tElement
                        .getElementsByTagName(
                                "Sobrenome")
                        .item(0)
                        .getTextContent();
                escritor.setId(Integer.parseInt(id));
                escritor.setPrimeiroNome(nome);
                escritor.setSobreNome(sobrenome);
                escritores.add(escritor);
            }
        }
        return escritores;
    }

      public List<Editora> readEditoras(String path) throws XPathExpressionException, SAXException, ParserConfigurationException, IOException {
        File file = new File(path);
        List<Editora> editoras = new ArrayList<>();

        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        doc.getDocumentElement().normalize();
        doc.getDocumentElement().getNodeName();
        NodeList nodeList
                = doc.getElementsByTagName("Editora");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Editora editora = new Editora();
            Node node = nodeList.item(i);
            node.getNodeName();
            if (node.getNodeType()
                    == Node.ELEMENT_NODE) {
                Element tElement = (Element) node;
                String id
                        = tElement
                                .getElementsByTagName("Id")
                                .item(0)
                                .getTextContent();
                String nome = tElement
                        .getElementsByTagName(
                                "Nome")
                        .item(0)
                        .getTextContent();
               
                editora.setIdEditora(Integer.parseInt(id));
                editora.setNome(nome);
 
                editoras.add(editora);
            }
        }
        return editoras;
    }
    
    public List<Livro> readLivros(String path) throws XPathExpressionException, SAXException, ParserConfigurationException, IOException {
        File file = new File(path);
        List<Livro> livros = new ArrayList<>();

        DocumentBuilderFactory dbf
                = DocumentBuilderFactory.newInstance();

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(file);

        doc.getDocumentElement().normalize();
        doc.getDocumentElement().getNodeName();
        NodeList nodeList
                = doc.getElementsByTagName("Livro");

        for (int i = 0; i < nodeList.getLength(); ++i) {
            Livro livro = new Livro();
            Node node = nodeList.item(i);
            node.getNodeName();
            if (node.getNodeType()
                    == Node.ELEMENT_NODE) {
                Element tElement = (Element) node;
                String id
                        = tElement
                                .getElementsByTagName("Id")
                                .item(0)
                                .getTextContent();
                String titulo = tElement
                        .getElementsByTagName(
                                "Titulo")
                        .item(0)
                        .getTextContent();
                String genero = tElement
                        .getElementsByTagName(
                                "Genero")
                        .item(0)
                        .getTextContent();

                String isbn = tElement
                        .getElementsByTagName(
                                "Isbn")
                        .item(0)
                        .getTextContent();

                String preco = tElement
                        .getElementsByTagName(
                                "Preco")
                        .item(0)
                        .getTextContent();
                String idescritor = tElement
                        .getElementsByTagName(
                                "IdEscritor")
                        .item(0)
                        .getTextContent();
                

                livro.setId(Integer.valueOf(id));
                System.out.println(titulo);
                livro.setTitulo(titulo);
                livro.setGenero(genero);
                livro.setIsbn(isbn);
                livro.setPreco(Double.valueOf(preco));
                livro.setIdEscritor(Integer.parseInt(idescritor));
                livros.add(livro);
            }
        }
        return livros;
    }

}
