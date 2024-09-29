package org.example.fileSearch;

import java.util.List;

public interface FileSearch {
    List<String> search(String word);
    void setSubstringSearch(boolean enabled);

    boolean isSubstringSearchEnabled();

    void setCaseSensitive(boolean enabled);

    boolean isCaseSensitive();
}
