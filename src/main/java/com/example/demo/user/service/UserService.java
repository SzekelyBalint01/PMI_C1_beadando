package com.example.demo.user.service;

import com.example.demo.user.controller.dto.DeleteResponse;
import com.example.demo.user.controller.dto.SaveResponse;
import com.example.demo.user.controller.dto.SearchResponse;
import org.springframework.stereotype.Service;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.util.MultiValueMap;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


@Service
public class UserService {

    public boolean searchUser(String azon) {

        //felhasznalo keresése
        try{
            File inputFile = new File("src/main/resources/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("USER");


            for (int index = 0; index< users.getLength(); index++){

                Node nNode = users.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("ADOAZON").item(0).getTextContent().equals(azon)){
                        return true;
                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
    public SearchResponse actualleData(String adoazon){
        SearchResponse sr = new SearchResponse();
        try{
            File inputFile = new File("src/main/resources/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("USER");


            for (int index = 0; index< users.getLength(); index++){

                Node nNode = users.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("ADOAZON").item(0).getTextContent().equals(adoazon)){

                        sr.setAdoazon(eElement.getElementsByTagName("ADOAZON").item(0).getTextContent());
                        sr.setFname(eElement.getElementsByTagName("FIRSTNAME").item(0).getTextContent());
                        sr.setLname(eElement.getElementsByTagName("LASTNAME").item(0).getTextContent());
                        sr.setTelephely(eElement.getElementsByTagName("TELEPHELY").item(0).getTextContent());
                        sr.setSzervezet(eElement.getElementsByTagName("SZERVEZET").item(0).getTextContent());
                        sr.setBelepeskelte(eElement.getElementsByTagName("LOGIN").item(0).getTextContent());
                        sr.setKilepeskelte(eElement.getElementsByTagName("LOGOUT").item(0).getTextContent());
                        sr.setBruttoalapber(eElement.getElementsByTagName("BRUTTOALAPBER").item(0).getTextContent());
                        sr.setCegneve(eElement.getElementsByTagName("CEGNEV").item(0).getTextContent());
                        sr.setCegadoszama(eElement.getElementsByTagName("CEGADOSZAM").item(0).getTextContent());
                        sr.setCegszekhelye(eElement.getElementsByTagName("CEGSZEKHELY").item(0).getTextContent());
                        sr.setLedolgnap(eElement.getElementsByTagName("LEDOLGOZOTTNAP").item(0).getTextContent());
                        sr.setMunkaszunnap(eElement.getElementsByTagName("MUNKASZUNETINAP").item(0).getTextContent());
                        sr.setTorzsber(eElement.getElementsByTagName("TORZSBER").item(0).getTextContent());




                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return sr;
    }



    public void modifyData(MultiValueMap<String, String> formData){
        SearchResponse sr = new SearchResponse();

        try{
            File inputFile = new File("src/main/resources/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("USER");


            for (int index = 0; index< users.getLength(); index++){

                Node nNode = users.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("ADOAZON").item(0).getTextContent().equals(formData.getFirst("azon"))){

                        eElement.getElementsByTagName("FIRSTNAME").item(0).setTextContent(formData.getFirst("fname"));
                        eElement.getElementsByTagName("LASTNAME").item(0).setTextContent(formData.getFirst("lname"));
                        eElement.getElementsByTagName("TELEPHELY").item(0).setTextContent(formData.getFirst("telephely"));
                        eElement.getElementsByTagName("SZERVEZET").item(0).setTextContent(formData.getFirst("szervezet"));
                        eElement.getElementsByTagName("LOGIN").item(0).setTextContent(formData.getFirst("belepeskelte"));
                        eElement.getElementsByTagName("LOGOUT").item(0).setTextContent(formData.getFirst("kilepeskelte"));
                        eElement.getElementsByTagName("BRUTTOALAPBER").item(0).setTextContent(formData.getFirst("bruttoalapber"));
                        eElement.getElementsByTagName("CEGNEV").item(0).setTextContent(formData.getFirst("cegneve"));
                        eElement.getElementsByTagName("CEGADOSZAM").item(0).setTextContent(formData.getFirst("cegadoszama"));
                        eElement.getElementsByTagName("CEGSZEKHELY").item(0).setTextContent(formData.getFirst("cegszekhelye"));
                        eElement.getElementsByTagName("LEDOLGOZOTTNAP").item(0).setTextContent(formData.getFirst("ledolgnap"));
                        eElement.getElementsByTagName("MUNKASZUNETINAP").item(0).setTextContent(formData.getFirst("munkaszunnap"));
                        eElement.getElementsByTagName("TORZSBER").item(0).setTextContent(formData.getFirst("torzsber"));

                        try (FileOutputStream output =
                                     new FileOutputStream("src/main/resources/UsersData.xml")) {
                            writeXml(doc, output);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    private static void writeXml(Document doc,
                                 OutputStream output)
            throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }

    public SaveResponse saveNewUser(MultiValueMap<String, String> formData) throws IOException, SAXException, ParserConfigurationException, TransformerException {
        File inputFile = new File("src/main/resources/UsersData.xml");
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.parse(inputFile);
        Element root = document.getDocumentElement();
        Element user = document.createElement("USER");
        root.appendChild(user);




            Element adoazon = document.createElement("ADOAZON");
            Element fname = document.createElement("FIRSTNAME");
            Element lname = document.createElement("LASTNAME");
            Element telephely = document.createElement("TELEPHELY");
            Element szervezet = document.createElement("SZERVEZET");
            Element login = document.createElement("LOGIN");
            Element logout = document.createElement("LOGOUT");
            Element brutto = document.createElement("BRUTTOALAPBER");
            Element cegnev = document.createElement("CEGNEV");
            Element cegadoszam = document.createElement("CEGADOSZAM");
            Element cegszekhely = document.createElement("CEGSZEKHELY");
            Element ledolgozottnap = document.createElement("LEDOLGOZOTTNAP");
            Element munkaszunetinap = document.createElement("MUNKASZUNETINAP");
            Element torzsber = document.createElement("TORZSBER");

            adoazon.appendChild(document.createTextNode(formData.getFirst("azon")));
            fname.appendChild(document.createTextNode(formData.getFirst("fname")));
            lname.appendChild(document.createTextNode(formData.getFirst("lname")));
            telephely.appendChild(document.createTextNode(formData.getFirst("telephely")));
            szervezet.appendChild(document.createTextNode(formData.getFirst("szervezet")));
            login.appendChild(document.createTextNode(formData.getFirst("belepeskelte")));
            logout.appendChild(document.createTextNode(formData.getFirst("kilepeskelte")));
            brutto.appendChild(document.createTextNode(formData.getFirst("bruttoalapber")));
            cegnev.appendChild(document.createTextNode(formData.getFirst("cegneve")));
            cegadoszam.appendChild(document.createTextNode(formData.getFirst("cegadoszama")));
            cegszekhely.appendChild(document.createTextNode(formData.getFirst("cegszekhelye")));
            ledolgozottnap.appendChild(document.createTextNode(formData.getFirst("ledolgnap")));
            munkaszunetinap.appendChild(document.createTextNode(formData.getFirst("munkaszunnap")));
            torzsber.appendChild(document.createTextNode(formData.getFirst("torzsber")));

            user.appendChild(adoazon);
            user.appendChild(fname);
            user.appendChild(lname);
            user.appendChild(telephely);
            user.appendChild(szervezet);
            user.appendChild(login);
            user.appendChild(logout);
            user.appendChild(brutto);
            user.appendChild(cegnev);
            user.appendChild(cegadoszam);
            user.appendChild(cegszekhely);
            user.appendChild(ledolgozottnap);
            user.appendChild(munkaszunetinap);
            user.appendChild(torzsber);

        SaveResponse sr = new SaveResponse();


        try (FileOutputStream output =
                     new FileOutputStream("src/main/resources/UsersData.xml")) {
            writeXml(document, output);
            sr.setSaved(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sr;
    }

    public DeleteResponse deleteUser( MultiValueMap<String, String> formData) {


        try{
            File inputFile = new File("src/main/resources/UsersData.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = factory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList users = doc.getElementsByTagName("USER");


            for (int index = 0; index< users.getLength(); index++){

                Node nNode = users.item(index);

                if (nNode.getNodeType() == Node.ELEMENT_NODE){

                    Element eElement = (Element) nNode;

                    if (eElement.getElementsByTagName("ADOAZON").item(0).getTextContent().equals(formData.getFirst("azon"))){

                        doc.getDocumentElement().removeChild(nNode);

                        try (FileOutputStream output =
                                     new FileOutputStream("src/main/resources/UsersData.xml")) {
                            writeXml(doc, output);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }


        DeleteResponse dr = new DeleteResponse();
        dr.setDeleted(true);

        return dr;
    }
}
