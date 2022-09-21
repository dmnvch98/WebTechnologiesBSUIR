package lab3.utils;

import lab3.entities.User;
import lab3.parsers.Parseble;
import lab3.parsers.domParser.DomXmlParser;
import lab3.parsers.saxParser.SaxXmlParser;
import lab3.parsers.staxParser.StaxXmlParser;

import java.util.Scanner;

public final class Utils {
    private Utils(){};

    public static Parseble<User> selectParser() {
        System.out.println("Select parser:\n1. Dom\n2. Sax\n3. Stax\nEnter value: ");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "1":
                return new DomXmlParser();
            case "2":
                return new SaxXmlParser();
            case "3":
                return new StaxXmlParser();
            default:
                System.out.println("Incorrect value entered");
        }
        return null;
    }
}
