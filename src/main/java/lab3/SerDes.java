package lab3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class SerDes {
    public static void main(String[] args) {
        File file = new File("test.txt");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("file.bin");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(file);

            FileInputStream fileInputStream = new FileInputStream("file.bin");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            File file1 = (File) objectInputStream.readObject();
            try (Stream<String> stream = Files.lines(Paths.get(file1.getPath()))) {
                stream.forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
