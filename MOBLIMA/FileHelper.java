package MOBLIMA;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * FileHelper class to assist in reading and writing Serializable Classes to files.
 * @author SS7 Group 1
 * @version 1.0
 * @since 2022-11-07
 */
public class FileHelper {
    /**
     * Reads a file that stores Serializable Classes.
     * @param fileName File to be read from.
     * @return List: List with contents of the file.
     */
    public static List read(String fileName) {
        List list = new ArrayList();
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(fileName);
            in = new ObjectInputStream(fis);
            list = (ArrayList) in.readObject();
            in.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    /**
     * Writes to a file that stores Serializable Classes.
     * @param list List with contents to be written.
     * @param fileName File to be written to.
     */
    public static void write(List list, String fileName) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(fileName);
            out = new ObjectOutputStream(fos);
            out.writeObject(list);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
