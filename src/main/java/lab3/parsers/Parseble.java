package lab3.parsers;

import java.io.File;
import java.util.List;

public interface Parseble<T> {
    List<T> parseXml(File file);
}
