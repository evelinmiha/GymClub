package org.example;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


/**
 * A generic class for saving and loading data to and from a file.
 * Uses Java serialization to store a list of objects of type T.
 *
 * @param <T> The type of objects this handler will manage.
 */
public class DataHandler <T> {
    // The name of the file where the data will be saved/loaded
    private String fileName;
    // Constructor: initializes the file name
    public DataHandler(String fileName) {
        this.fileName = fileName;
    }
    /**
     * Saves a list of objects to a file using object serialization.
     * @param data The list of objects to save.
     */
    public void save(List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(data);  // Write the entire list to the file
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
    /**
     * Loads a list of objects from a file.
     * @return A list of objects, or an empty list if the file does not exist or cannot be read.
     */
    public List<T> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<T>) ois.readObject(); // Read and cast the object from file
        } catch (Exception e) {
            System.out.println("No existing data found.");
            return new ArrayList<>();  // Return empty list if file not found or error occurs
        }
    }
}
