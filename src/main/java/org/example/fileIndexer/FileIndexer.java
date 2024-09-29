package org.example.fileIndexer;
import java.io.File;
import java.util.List;
import java.util.Map;

public interface FileIndexer {
    void index(File fileOrDirectory);

    Map<String, List<File>> getIndex();
}
