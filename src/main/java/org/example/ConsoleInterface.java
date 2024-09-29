package org.example;

import org.example.tokenizer.SimpleTokenizer;
import org.example.fileIndexer.FileIndexerImpl;
import org.example.fileSearch.FileSearchImpl;

import java.io.File;
import java.util.List;
import java.util.Scanner;

    public class ConsoleInterface {

        private final static String ANSI_RESET = "\u001B[0m";
        private final static String ANSI_RED = "\u001B[31m";
        private final FileIndexerImpl fileIndexer;
        private final FileSearchImpl fileSearch;
        private final Scanner scanner;

        public ConsoleInterface() {
            SimpleTokenizer tokenizer = new SimpleTokenizer();

            // Initialize indexer and search
            this.fileIndexer = new FileIndexerImpl(tokenizer);
            this.fileSearch = new FileSearchImpl(fileIndexer);

            // Initialize scanner for user input
            this.scanner = new Scanner(System.in);
        }

        public void start() {
            System.out.println("Welcome to the file indexing and search system.");
            boolean exit = false;

            while (!exit) {
                printMenu();
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "1":
                        indexFilesOrDirectories();
                        break;
                    case "2":
                        searchWord();
                        break;
                    case "3":
                        toggleSubstringSearch();
                        break;
                    case "4":
                        toggleCaseSensitiveSearch();
                        break;
                    case "5":
                        clearConsole();
                        break;
                    case "6":
                        clearIndex();
                        break;
                    case "9":
                        exit = true;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println(ANSI_RED + "Unknown command. Please try again." + ANSI_RESET);
                }
            }
        }

        private void clearConsole() {
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("clear").inheritIO().start().waitFor();
                }
            } catch (Exception e) {
                System.out.println(ANSI_RED + "Error clearing console: " + e.getMessage() + ANSI_RESET);
            }
        }

        private void printMenu() {

            System.out.println("\nSelect an option:");
            System.out.println("1. Specify files or directories to index.");
            System.out.println("2. Search for files containing a word.");
            System.out.println("3. Toggle substring search mode (current: "
                    + (fileSearch.isSubstringSearchEnabled() ? "enabled" : "disabled") + ").");
            System.out.println("4. Toggle case-sensitive search mode (current: "
                    + (fileSearch.isCaseSensitive() ? "enabled" : "disabled") + ").");
            System.out.println("5. Clear the console.");
            System.out.println("6. Clear the index.");
            System.out.println("9. Exit.");
            System.out.print("Enter your command number: ");
        }

        private void indexFilesOrDirectories() {
            System.out.print("Enter the path to the file or directory to index (comma-separated paths are allowed): ");
            String inputPath = scanner.nextLine().trim();
            String[] paths = inputPath.split(",");

            for (String path : paths) {
                File fileOrDirectory = new File(path.trim());
                if (fileOrDirectory.exists()) {
                    fileIndexer.index(fileOrDirectory);
                    System.out.println("Indexed: " + fileOrDirectory.getPath());
                } else {
                    System.out.println(ANSI_RED + "Path not found: " + fileOrDirectory.getPath() + ANSI_RESET);
                }
            }
        }

        private void searchWord(){
            if(fileIndexer.getIndex().isEmpty()){
                System.out.println(ANSI_RED + "Index is empty. Please index files or directories first." + ANSI_RESET);
                return;
            }
            System.out.print("Enter the word to search for: ");
            String word = scanner.nextLine().trim();
            List<String> results = fileSearch.search(word);

            if (results.isEmpty()) {
                System.out.println("No files found containing the word '" + word + "'.");
            } else {
                System.out.println("Files containing the word '" + word + "':");
                for (String file : results) {
                    System.out.println(file);
                }
            }
        }

        private void toggleSubstringSearch() {
            // Toggle substring search mode
            boolean currentState = fileSearch.isSubstringSearchEnabled();
            fileSearch.setSubstringSearch(!currentState);
            System.out.println("Substring search mode " + (!currentState ? "enabled" : "disabled") + ".");
        }

        private void toggleCaseSensitiveSearch() {
            // Toggle case-sensitive search mode
            boolean currentState = fileSearch.isCaseSensitive();
            fileSearch.setCaseSensitive(!currentState);
            System.out.println("Case-sensitive search mode " + (!currentState ? "enabled" : "disabled") + ".");
        }

        private void clearIndex() {
            fileIndexer.clearIndex();
            System.out.println("Index cleared.");
        }

        public static void main(String[] args) {
            ConsoleInterface consoleInterface = new ConsoleInterface();
            consoleInterface.start();
        }
    }