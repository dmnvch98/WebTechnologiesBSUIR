package lab3;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class DomXmlParser {
    public String firstName;
    public String lastName;
    public String title;

    public List<User> parseXml(File file) {
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

            // Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
            NodeList usersList = doc.getDocumentElement().getElementsByTagName("user");
            List<User> users = new ArrayList<>();

            // Перебор всех элементов employee
            for (int i = 0; i < usersList.getLength(); i++) {
                Node node = usersList.item(i);
                Element element = (Element) node;

                // Получение атрибутов каждого элемента
                String username = element.getElementsByTagName("username").item(0).getTextContent();
                int salary = Integer.parseInt(element.getElementsByTagName("salary").item(0).getTextContent());
                boolean isMarried = Boolean.parseBoolean(
                        element.getAttribute("married")
                );
                User user = new User();
                user.setUsername(username);
                user.setSalary(salary);
                user.setMarried(isMarried);
                users.add(user);
            }


//            NodeList list = doc.getDocumentElement().getElementsByTagName("line");
//            List<String> linesList = IntStream.range(0, list.getLength())
//                    .mapToObj(list::item)
//                    .map(Node::getTextContent)
//                    .map(x -> x.replace("\n", ""))
//                    .map(String::trim)
//                    .toList();

            return users;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
