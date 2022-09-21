package lab3.parsers.domParser;

import lab3.entities.User;
import lab3.parsers.Parseble;
import lab3.parsers.XsdValidator;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DomXmlParser implements Serializable, Parseble<User> {
    XsdValidator xsdValidator = new XsdValidator();

    @Override
    public List<User> parseXml(File file) {
        if (xsdValidator.validateXMLSchema(file)) {
            // Instantiate the Factory
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            try {
                // optional, but recommended
                // process XML securely, avoid attacks like XML External Entities (XXE)
                dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

                // parse XML file
                DocumentBuilder db = dbf.newDocumentBuilder();

                Document doc = db.parse(file);

                // optional, but recommended
                // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
                doc.getDocumentElement().normalize();

                NodeList list = doc.getDocumentElement().getElementsByTagName("user");

                return IntStream.range(0, list.getLength())
                        .mapToObj(list::item)
                        .map(x -> (Element) x)
                        .map(x -> {
                            String username = x.getElementsByTagName("username").item(0).getTextContent();
                            int salary = Integer.parseInt(x.getElementsByTagName("salary").item(0).getTextContent());
                            boolean isMarried = Boolean.parseBoolean(
                                    x.getElementsByTagName("married").item(0).getTextContent()
                            );
                            int id = Integer.parseInt(x.getAttribute("id"));
                            User user = new User();
                            user.setUsername(username);
                            user.setSalary(salary);
                            user.setMarried(isMarried);
                            user.setId(id);
                            return user;
                        })
                        .toList();
            } catch (ParserConfigurationException | SAXException | IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
            return new ArrayList<>();
        }
    }
}
