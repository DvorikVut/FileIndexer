package org.example;

import org.example.tokenizer.SimpleTokenizer;
import org.example.tokenizer.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleTokenizerTest {

    @Test
    public void testTokenizeSimpleText() {
        Tokenizer tokenizer = new SimpleTokenizer();
        String text = "This is a simple test";
        List<String> tokens = tokenizer.tokenize(text);

        assertEquals(5, tokens.size());
        assertTrue(tokens.contains("This"));
        assertTrue(tokens.contains("is"));
        assertTrue(tokens.contains("a"));
        assertTrue(tokens.contains("simple"));
        assertTrue(tokens.contains("test"));
    }

    @Test
    public void testTokenizeWithMultipleSpaces() {
        Tokenizer tokenizer = new SimpleTokenizer();
        String text = "Sample     text     content.";
        List<String> tokens = tokenizer.tokenize(text);

        assertEquals(3, tokens.size());
        assertTrue(tokens.contains("Sample"));
        assertTrue(tokens.contains("text"));
        assertTrue(tokens.contains("content"));
    }

    @Test
    public void testTokenizeWithPunctuation() {
        Tokenizer tokenizer = new SimpleTokenizer();
        String text = "Hello, world! This is a test.";
        List<String> tokens = tokenizer.tokenize(text);

        assertEquals(6, tokens.size());
        assertTrue(tokens.contains("Hello"));
        assertTrue(tokens.contains("world"));
        assertTrue(tokens.contains("This"));
        assertTrue(tokens.contains("is"));
        assertTrue(tokens.contains("a"));
        assertTrue(tokens.contains("test"));
    }

    @Test
    public void testTokenizeEmptyString() {
        Tokenizer tokenizer = new SimpleTokenizer();
        String text = "";
        List<String> tokens = tokenizer.tokenize(text);

        assertTrue(tokens.isEmpty());
    }
}
