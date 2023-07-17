# Pattern Management Project

This project is a Java Spring Boot application for managing pattern calls. It provides functionality to load pattern call data from a file, perform queries on the data, and output the query results to a file.

## Project Structure

The project consists of the following files:

- `PatternCall.java`: Represents a pattern call entity.
- `PatternCallService.java`: Service class for managing pattern calls and performing queries.
- `PatternManagementApplication.java`: Main application class to run the project.
- `pattern_calls.txt`: Input file containing the pattern call data (comma-separated values).
- `pattern_calls_output.txt`: Output file to which the query results are written.
- `PatternManagementApplicationTests.java`: Test file containing unit tests for the `PatternCallService` class.

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository: `git clone <repository_url>` OR Download from Drive(zip folder)
2. Open the project in IDE.
3. Build the project to resolve dependencies. (Maven Build)
4. Ensure that the `pattern_calls.txt` input file is present in the classpath. Update the file with the desired pattern call data if needed.
5. Run the `PatternManagementApplication` class.

## Usage

The application provides the following functionality:

- Loading pattern call data from a file.
- Performing queries on the pattern call data.
- Writing the query results to an output file.

The queries performed by the application are as follows:

1. Pattern Call with ID 42: Retrieves the pattern call with the specified ID.
2. Pattern Calls with Name 'myPattern': Retrieves all pattern calls with the specified name.
3. Pattern Calls with Path 'src/patterns/Functional.pat': Retrieves all pattern calls with the specified file path.
4. Skipped Pattern Calls: Retrieves all pattern calls that are marked as skipped.
5. Not Skipped Pattern Calls: Retrieves all pattern calls that are not marked as skipped.

The query results are written to the `pattern_calls_output.txt` file and for testing purposes on System Console.
Once the project is built and loaded, the output file will be at path  target->Classes->pattern_calls_output.txt

## Test Files

The project includes unit tests written using the JUnit framework. The test file `PatternManagementApplicationTests.java` contains the following test cases:

1. `testLoadPatternCallsFromFile`: Tests the successful loading of pattern call data from valid file content.
2. `testGetPatternCallById`: Tests the retrieval of a pattern call by ID when the pattern call exists.
3. `testGetPatternCallByIdReturnsNull`: Tests the retrieval of a pattern call by ID when the pattern call does not exist.
4. `testGetPatternCallsByName`: Tests the retrieval of pattern calls by name when pattern calls with the specified name exist.
5. `testGetPatternCallsByNameReturnsEmptyList`: Tests the retrieval of pattern calls by name when no pattern calls with the specified name exist.
6. `testDumpQueriesToFile`: Tests the successful writing of queries to the output file. Test file->(pattern_calls_output_test.txt)


## Assumptions and Limitations

- The input file (`pattern_calls.txt`) should follow a specific format: each line represents a pattern call entry with comma-separated values (ID, Name, Pattern File, Called).
- The input file should be present in the classpath and should be named `pattern_calls.txt`.
- The output file (`pattern_calls_output.txt`) is overwritten with the query results each time the application is run.
- The application assumes that the input file is correctly formatted and does not perform extensive validation on the data.

## Dependencies

The project uses the following dependencies:

- Spring Boot: for creating the application.
- Maven for project Build
- Lombok: for generating boilerplate code.
- JUnit: for unit testing.
