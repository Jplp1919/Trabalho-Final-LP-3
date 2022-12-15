package trab03.doc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DocumentManipulatorXML {
    static final Logger logger = Logger.getLogger(DocumentManipulatorXML.class.getName());

    public static Document newDocument() {
        try {

            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            return dbF.newDocumentBuilder().newDocument();

        } catch (ParserConfigurationException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public static XPathExpression getXPathExpression(String xpath) {
        try {

            XPath localxpath = XPathFactory.newInstance().newXPath();
            XPathExpression expr = localxpath.compile(xpath);
            return expr;

        } catch (XPathExpressionException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static String xmltoString(Document doc) {
        try {

            Source source = new DOMSource(doc);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            Result result = new StreamResult(stream);
            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.transform(source, result);
            return stream.toString();

        } catch (TransformerFactoryConfigurationError | IllegalArgumentException | TransformerException ex) {
          logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static Document stringtoXml(String text) {
        try {

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            return builder.parse(new ByteArrayInputStream(text.getBytes()));

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public static Document readXmlFile(String filename) {
        try {

            DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
            domFactory.setNamespaceAware(true);
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            return builder.parse(filename);

        } catch (IOException | SAXException | ParserConfigurationException ex) {
           logger.log(Level.SEVERE, null, ex);
            return null;
        }
    }


    public static void writeXmlFile(Document doc, String filename) {
        try {

            Source source = new DOMSource(doc);
            File file = new File(filename);
            Result result = new StreamResult(file);

          result.toString();

            Transformer xformer = TransformerFactory.newInstance().newTransformer();
            xformer.setOutputProperty(OutputKeys.INDENT, "yes");
            xformer.transform(source, result);

        } catch (TransformerException | TransformerFactoryConfigurationError ex) {
            logger.log(Level.SEVERE, null, ex);
        }
    }
}
