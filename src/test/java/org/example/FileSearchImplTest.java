package org.example;

import org.example.fileIndexer.FileIndexer;
import org.example.fileIndexer.FileIndexerImpl;
import org.example.fileSearch.FileSearchImpl;
import org.example.tokenizer.SimpleTokenizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class FileSearchImplTest {
    private FileSearchImpl fileSearch;
    private FileIndexer fileIndexer;

    @BeforeEach
    public void setup() {
        fileIndexer = new FileIndexerImpl(new SimpleTokenizer());
        fileSearch = new FileSearchImpl(fileIndexer);

        File directory = new File("src/test/resources/sample-dir");
        fileIndexer.index(directory);
    }

    @Test
    public void testExactMatchSearch() {
        fileSearch.setSubstringSearch(false); // Disable substring search
        fileSearch.setCaseSensitive(false); // Case-insensitive

        List<String> results = fileSearch.search("sample");
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    @Test
    public void testSubstringSearch() {
        fileSearch.setSubstringSearch(true); // Enable substring search
        fileSearch.setCaseSensitive(false); // Case-insensitive

        List<String> results = fileSearch.search("sam");
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }

    @Test
    public void testCaseSensitiveSearch() {
        fileSearch.setSubstringSearch(false); // Disable substring search
        fileSearch.setCaseSensitive(true); // Case-sensitive

        List<String> results = fileSearch.search("another");
        assertTrue(results.isEmpty());

        results = fileSearch.search("Another");
        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
    }

    @Test
    public void testCaseInsensitiveSearch() {
        fileSearch.setSubstringSearch(false); // Disable substring search
        fileSearch.setCaseSensitive(false); // Case-insensitive

        List<String> results = fileSearch.search("Sample");
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
    }
}
