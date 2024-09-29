package org.example;

import org.example.fileIndexer.FileIndexer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.example.fileIndexer.FileIndexerImpl;
import org.example.tokenizer.SimpleTokenizer;
import java.io.File;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class FileIndexerImplTest {
    private FileIndexer fileIndexer;

    @BeforeEach
    public void setup() {
        fileIndexer = new FileIndexerImpl(new SimpleTokenizer());
    }

    @Test
    public void testIndexSingleFile() {
        File file = new File("src/test/resources/sample.txt");
        fileIndexer.index(file);

        Map<String, List<File>> index = fileIndexer.getIndex();
        assertFalse(index.isEmpty());
        assertTrue(index.containsKey("sample"));
        assertTrue(index.containsKey("text"));
        assertTrue(index.containsKey("file"));

        List<File> files = index.get("sample");
        assertEquals(1, files.size());
        assertEquals(file, files.get(0));
    }

    @Test
    public void testIndexDirectory() {
        File directory = new File("src/test/resources/sample-dir");
        fileIndexer.index(directory);

        Map<String, List<File>> index = fileIndexer.getIndex();
        assertFalse(index.isEmpty());

        assertTrue(index.containsKey("file"));
        assertTrue(index.containsKey("Another"));
        assertTrue(index.containsKey("sample"));

        assertEquals(1, index.get("file").size()); // Two files containing "file"
    }
}
