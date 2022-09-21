package lab3.parsers.saxParser;

import lab3.entities.User;
import lab3.parsers.Parseble;
import lab3.parsers.XsdValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SaxXmlParser implements Serializable, Parseble<User> {
    XsdValidator xsdValidator = new XsdValidator();
    @Override
    public List<User> parseXml(File file) {
        if (xsdValidator.validateXMLSchema(file)) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser parser = factory.newSAXParser();
                Handler handler = new Handler();
                parser.parse(file, handler);
                return handler.getEmpList();
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            return new ArrayList<>();
        }
    }
}
