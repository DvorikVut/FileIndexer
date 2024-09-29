package org.example.fileIndexer;

import org.example.tokenizer.Tokenizer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class FileIndexerImpl implements FileIndexer {
    private final Tokenizer tokenizer;
    private final Map<String, List<File>> index;

    public FileIndexerImpl(Tokenizer tokenizer) {
        this.tokenizer = tokenizer;
        this.index = new HashMap<>();
    }


    @Override
    public void index(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] files = fileOrDirectory.listFiles();
            if (files != null) {
                for (File file : files) {
                    index(file);
                }
            }
        } else {
            try {
                List<String> lines = Files.readAllLines(fileOrDirectory.toPath());
                for (String line : lines) {
                    List<String> tokens = tokenizer.tokenize(line);
                    for (String token : tokens) {
                        index.computeIfAbsent(token, k -> new ArrayList<>()).add(fileOrDirectory);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + fileOrDirectory.getPath());
            }
        }
    }

    @Override
    public Map<String, List<File>> getIndex() {
        return index;
    }

    public void clearIndex() {
        index.clear();
    }
}
