package src.filesystem;

import java.io.*;
import java.util.List;

public class FileHandler {
    public static void saveObjects(String filename, List<?> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<?> loadObjects(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Object readObject = ois.readObject();
            if (readObject instanceof List<?>) {
                return (List<?>) readObject;
            } else {
                return null;
            }
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }

}
