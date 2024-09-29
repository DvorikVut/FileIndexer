package org.example.fileSearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.fileIndexer.FileIndexer;

public class FileSearchImpl implements FileSearch {
    private final FileIndexer fileIndexer;
    private boolean isSubstringSearchEnabled;
    private boolean isCaseSensitive;

    public FileSearchImpl(FileIndexer fileIndexer) {
        this.fileIndexer = fileIndexer;
        this.isSubstringSearchEnabled = false; // Substring search is off by default
        this.isCaseSensitive = false; // Case-insensitive search is on by default
    }

    @Override
    public List<String> search(String word) {
        Map<String, List<File>> index = fileIndexer.getIndex();
        List<String> resultFiles = new ArrayList<>();

        for (Map.Entry<String, List<File>> entry : index.entrySet()) {
            String key = isCaseSensitive ? entry.getKey() : entry.getKey().toLowerCase();
            String searchWord = isCaseSensitive ? word : word.toLowerCase();

            if (isSubstringSearchEnabled) {
                // Substring search
                if (key.contains(searchWord)) {
                    for (File file : entry.getValue()) {
                        if (!resultFiles.contains(file.getPath())) {
                            resultFiles.add(file.getPath());
                        }
                    }
                }
            } else {
                // Exact match search
                if (key.equals(searchWord)) {
                    for (File file : entry.getValue()) {
                        if (!resultFiles.contains(file.getPath())) {
                            resultFiles.add(file.getPath());
                        }
                    }
                }
            }
        }
        return resultFiles;
    }

    @Override
    public void setSubstringSearch(boolean enabled) {
        this.isSubstringSearchEnabled = enabled;
    }

    @Override
    public boolean isSubstringSearchEnabled() {
        return isSubstringSearchEnabled;
    }

    @Override
    public void setCaseSensitive(boolean enabled) {
        this.isCaseSensitive = enabled;
    }

    @Override
    public boolean isCaseSensitive() {
        return isCaseSensitive;
    }
}
