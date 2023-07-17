package pattern.patternManagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Service class for managing pattern calls.
 */
public class PatternCallService {

    // List to store the tuples data for pattern calls
    private List<PatternCall> patternCalls;

    public PatternCallService() {
        patternCalls = new ArrayList<>();
    }

    /**
     * Loads pattern call data from a file.
     *
     * @param fileContent the content of the file containing pattern call data
     */
    public void loadPatternCallsFromFile(String fileContent) {
        try {
        //Split the lines from file content
        String[] lines = fileContent.split("\\r?\\n");
        for (String line : lines) {
            String[] parts = line.split(",\\s*");
                if (parts.length == 4) {
                    try{
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1].replaceAll("\"", "");
                            //.substring(1, parts[1].length() - 1);
                    String patternFile = parts[2];
                    boolean called = Boolean.parseBoolean(parts[3]);
                    PatternCall patternCall = new PatternCall(id, name, patternFile, called);
                    patternCalls.add(patternCall);
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid ID format: " + parts[0]);
                    }
                }else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (Exception e) {
            System.err.println("Error occurred while loading pattern calls: " + e.getMessage());
        }
    }

    /**
     * Retrieves a pattern call by its ID.
     *
     * @param id the ID of the pattern call
     * @return the pattern call with the specified ID, or null if not found
     */
    public PatternCall getPatternCallById(int id) {
        return patternCalls.stream()
                .filter(patternCall -> patternCall.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Retrieves pattern calls by name.
     *
     * @param name the name of the pattern calls
     * @return a list of pattern calls with the specified name
     */
    public List<PatternCall> getPatternCallsByName(String name) {
        return patternCalls.stream()
                .filter(patternCall -> patternCall.getName().equals(name))
                .toList();
    }

    /**
     * Retrieves pattern calls by file path.
     *
     * @param path the file path of the pattern calls
     * @return a list of pattern calls with the specified file path
     */
    public List<PatternCall> getPatternCallsByPath(String path) {
        return patternCalls.stream()
                .filter(patternCall -> patternCall.getPatternFile().equals(path))
                .toList();
    }

    /**
     * Retrieves skipped pattern calls.
     *
     * @return a list of skipped pattern calls
     */
    public List<PatternCall> getSkippedPatternCalls() {
        return patternCalls.stream()
                .filter(patternCall -> !patternCall.isCalled())
                .toList();
    }

    /**
     * Retrieves called pattern calls.
     *
     * @return a list of called pattern calls
     */
    public List<PatternCall> getCalledPatternCalls() {
        return patternCalls.stream()
                .filter(PatternCall::isCalled)
                .toList();
    }

    /**
     * Dumps the queries to a file.
     *
     * @param file the file to write the queries to
     * @throws IOException if an I/O error occurs
     */
    public void dumpQueriesToFile(File file) throws IOException {
        try{
            PrintWriter  writer = new PrintWriter (new FileWriter(file));
            writer.println("Queries:");
            writer.println("Pattern Call with ID 42: ");
            writer.println(getPatternCallById(42));
            writer.println("Pattern Calls with Name 'myPattern': ");
            writer.println(getPatternCallsByName("myPattern"));
            writer.println("Pattern Calls with Path 'src/patterns/Functional.pat': ");
            writer.println(getPatternCallsByPath("src/patterns/Functional.pat"));
            writer.println("Skipped Pattern Calls: ");
            writer.println(getSkippedPatternCalls());
            writer.println("Not Skipped Pattern Calls: ");
            writer.println(getCalledPatternCalls());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
