package lab3;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // need host and port, we want to connect to the ServerSocket at port 7777
        Socket socket = new Socket("localhost", 7777);
        System.out.println("Connected!");

        // get the output stream from the socket.
        OutputStream outputStream = socket.getOutputStream();
        // create an object output stream from the output stream so we can send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        InputStream inputStream = socket.getInputStream();
        // create a DataInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // make a bunch of messages to send.
        File file = new File("src/main/java/lab3/users.xml");

        System.out.println("Sending file to the ServerSocket");
        objectOutputStream.writeObject(file);

        List<User> listOfUsers = (List<User>) objectInputStream.readObject();
        System.out.println(listOfUsers);

        System.out.println("Closing socket and terminating program.");
        socket.close();
    }
}