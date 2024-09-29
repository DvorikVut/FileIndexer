# File Indexer and Search Application

## Overview

This is a simple Java-based application for indexing and searching through text files. The application allows users to:
- Index files and directories.
- Search for files that contain specific words.
- Search for exact matches or substrings.
- Toggle between case-sensitive and case-insensitive search modes.
- The application is designed to be easily extensible, allowing different tokenization strategies.

## Features

- **File and Directory Indexing**: Index individual files or entire directories.
- **Tokenization**: Customizable tokenization logic (default is simple word-based tokenization).
- **Substring Search**: Ability to search for exact matches or substrings.
- **Case Sensitivity**: Toggle between case-sensitive and case-insensitive search.
- **No External Libraries**: No third-party libraries are used, making the app lightweight and simple to integrate.

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/DvorikVut/FileIndexer
    ```
2. Navigate to the project directory:
    ```bash
    cd FileIndexer
    ```
3. Compile the project with Maven:
    ```bash
    mvn clean install
    ```

## Usage

Once the project is compiled, you can run the application from the command line.

### Running the Application

1. Run the application:
    ```bash
    java -jar target/Index-1.0.jar
    ```

2. The console menu will appear:

    ```
    Select an option:
   1. Specify files or directories to index.
   2. Search for files containing a word.
   3. Toggle substring search mode (current: disabled).
   4. Toggle case-sensitive search mode (current: disabled).
   5. Clear the console.
   6. Clear the index.
   9. Exit.
   ```
   

3. Follow the instructions to interact with the application:
    - Option 1 allows you to specify the path to files or directories for indexing.
    - Option 2 allows you to search for a specific word within the indexed files.
    - Option 3 toggles substring search (on or off).
    - Option 4 toggles case-sensitive search (on or off).
    - Option 5 clears the console.
    - Option 6 clears the index.
    - Option 9 exits the application.

### Example

- To index files:
    ```
    Enter the path of the file or directory to index: src/test/resources/test-files/
    ```

- To search for a word:
    ```
    Enter the word to search: sample
    ```

- Toggle substring or case sensitivity search:
    ```
    Select an option:
    3. Toggle substring search mode
    4. Toggle case-sensitive search mode
    ```

## Tests

Unit tests are provided to ensure the correctness of the indexing and search functionalities.

### Running Tests

To run the tests, use Maven:
```bash
mvn test
