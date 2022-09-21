package lab3.entities;

import lab3.parsers.Parseble;
import lab3.parsers.domParser.DomXmlParser;
import lab3.parsers.saxParser.SaxXmlParser;
import lab3.parsers.staxParser.StaxXmlParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    private static final DomXmlParser domXmlParser = new DomXmlParser();
    private static final SaxXmlParser saxXmlParser = new SaxXmlParser();
    private static final StaxXmlParser staxXmlParser = new StaxXmlParser();
    static Parseble<User> parseble;
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // don't need to specify a hostname, it will be the current machine
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");
        Socket socket = ss.accept(); // blocking call, this will wait until a connection is attempted on this port.
        System.out.println("Connection from " + socket + "!");

        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream, so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream, so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        // read the file from the socket
        File file = (File) objectInputStream.readObject();
        parseble = (Parseble<User>) objectInputStream.readObject();
        System.out.println("Received " + file.getName() + "\nSelected parser: " + parseble.getClass().getSimpleName());

        List<User> users = parseble.parseXml(file);
        objectOutputStream.writeObject(users);

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
}