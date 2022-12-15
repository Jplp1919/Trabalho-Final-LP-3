/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package trab03.doc;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import javax.xml.xpath.XPathExpression;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import trab03.ConnectionFactory;

public class DocumentManipulatorXMLTest {

    Connection con;
    Document doc;

    @Before
    public void setUp() throws SQLException {
        doc = DocumentManipulatorXML.newDocument();
        con = new ConnectionFactory().establishConnection();
        con.setAutoCommit(false);

    }

    @After
    public void tearDown() throws SQLException {
        con.rollback();
    }

    @Test
    public void deveCriarXml() {
        doc.createElement("Teste");
        DocumentManipulatorXML.writeXmlFile(doc, "./testexml/TesteDocumentManipulatorXML.xml");
        File f = new File("./testexml/TesteDocumentManipulatorXML.xml");
        assertNotNull(f);

    }

    @Test
    public void deveLerUmXml() {
        Element master = doc.createElement("Teste");
        doc.appendChild(master);
        DocumentManipulatorXML.writeXmlFile(doc, "./testexml/TesteDocumentManipulatorXML.xml");

        Document testeDoc = DocumentManipulatorXML.readXmlFile("./testexml/TesteDocumentManipulatorXML.xml");
        String testeString = DocumentManipulatorXML.xmltoString(testeDoc);
        assertNotNull(testeString);

    }

}
