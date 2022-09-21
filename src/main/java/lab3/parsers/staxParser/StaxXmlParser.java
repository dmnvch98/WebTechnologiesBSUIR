package lab3.parsers.staxParser;

import lab3.entities.User;
import lab3.parsers.Parseble;
import lab3.parsers.XsdValidator;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StaxXmlParser implements Serializable, Parseble<User> {
    User user;
    XsdValidator xsdValidator = new XsdValidator();

    @Override
    public List<User> parseXml(File file) {
        if (xsdValidator.validateXMLSchema(file)) {
            try {
                XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(file));

                List<User> userList = new ArrayList<>();
                while (reader.hasNext()) {
                    XMLEvent nextEvent = reader.nextEvent();
                    if (nextEvent.isStartElement()) {
                        StartElement startElement = nextEvent.asStartElement();
                        switch (startElement.getName().getLocalPart()) {
                            case "user" -> {
                                user = new User();
                                Attribute url = startElement.getAttributeByName(new QName("id"));
                                if (url != null) {
                                    user.setId(Integer.valueOf(url.getValue()));
                                }
                            }
                            case "username" -> {
                                nextEvent = reader.nextEvent();
                                user.setUsername(nextEvent.asCharacters().getData());
                            }
                            case "married" -> {
                                nextEvent = reader.nextEvent();
                                user.setMarried(Boolean.parseBoolean(nextEvent.asCharacters().getData()));
                            }
                            case "salary" -> {
                                nextEvent = reader.nextEvent();
                                user.setSalary(Integer.valueOf(nextEvent.asCharacters().getData()));
                            }
                        }
                    }
                    if (nextEvent.isEndElement()) {
                        EndElement endElement = nextEvent.asEndElement();
                        if (endElement.getName().getLocalPart().equals("user")) {
                            userList.add(user);
                        }
                    }
                }
                return userList;
            } catch (XMLStreamException | FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            return new ArrayList<>();
        }
    }
}
