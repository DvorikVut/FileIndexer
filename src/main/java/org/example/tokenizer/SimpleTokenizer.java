package org.example.tokenizer;

import java.util.ArrayList;
import java.util.List;

public class SimpleTokenizer implements Tokenizer {

    @Override
    public List<String> tokenize(String text) {
        if (text == null || text.isEmpty()) {
            return new ArrayList<>();
        }
        // Split text by non-word characters
        String[] words = text.split("\\W+");
        List<String> tokens = new ArrayList<>();
        for (String word : words) {
            if (!word.isEmpty()) {
                tokens.add(word);
            }
        }
        return tokens;
    }
}
