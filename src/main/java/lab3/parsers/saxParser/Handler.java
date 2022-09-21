package lab3.parsers.saxParser;

import java.util.ArrayList;
import java.util.List;

import lab3.entities.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class Handler extends DefaultHandler {

	// List to hold Employees object
	private List<User> usersList = null;
	private User user = null;
	private StringBuilder data = null;

	// getter method for employee list
	public List<User> getEmpList() {
		return usersList;
	}

	boolean bUsername = false;
	boolean bSalary = false;
	boolean bMarried = false;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equalsIgnoreCase("User")) {
			// create a new Employee and put it in Map
			String id = attributes.getValue("id");
			// initialize Employee object and set id attribute
			user = new User();
			user.setId(Integer.parseInt(id));
			// initialize list
			if (usersList == null)
				usersList = new ArrayList<>();
		} else if (qName.equalsIgnoreCase("username")) {
			// set boolean values for fields, will be used in setting Employee variables
			bUsername = true;
		} else if (qName.equalsIgnoreCase("salary")) {
			bSalary = true;
		} else if (qName.equalsIgnoreCase("married")) {
			bMarried = true;
		}
		// create the data container
		data = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (bUsername) {
			// age element, set Employee age
			user.setUsername(data.toString());
			bUsername = false;
		} else if (bSalary) {
			user.setSalary(Integer.parseInt(data.toString()));
			bSalary = false;
		} else if (bMarried) {
			user.setMarried(Boolean.parseBoolean(data.toString()));
			bMarried = false;
		}
		
		if (qName.equalsIgnoreCase("User")) {
			// add Employee object to list
			usersList.add(user);
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		data.append(new String(ch, start, length));
	}
}