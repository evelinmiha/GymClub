package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class DataHandlerTest {

    @Test
    void testSaveAndLoad() {
        // Setup
        String fileName = "test_members.dat";
        DataHandler<Member> handler = new DataHandler<>(fileName);

        // Create sample data
        List<Member> originalList = new ArrayList<>();
        originalList.add(new Member("Alice", "Gold", java.time.LocalDate.now()));
        originalList.add(new Member("Bob", "Silver", java.time.LocalDate.now()));

        // Save
        handler.save(originalList);

        // Load
        List<Member> loadedList = handler.load();

        // Check size and simple data (we're not using equals)
        assertEquals(originalList.size(), loadedList.size());
        assertEquals(originalList.get(0).getName(), loadedList.get(0).getName());
        assertEquals(originalList.get(1).getName(), loadedList.get(1).getName());

        // Clean up
        new File(fileName).delete();
    }


    @Test
    void testLoadFromNonexistentFile() {
        DataHandler<Member> handler = new DataHandler<>("nonexistent_file.dat");

        List<Member> result = handler.load();

        // Should return an empty list
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}