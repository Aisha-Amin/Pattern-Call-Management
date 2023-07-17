package pattern.patternManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@SpringBootApplication
public class PatternManagementApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(PatternManagementApplication.class, args);

		// Create an instance of the PatternCallService
		PatternCallService patternCallService = new PatternCallService();

		// Read pattern call data from file
		Resource resource = new ClassPathResource("pattern_calls.txt");
		File file = resource.getFile();
		String content = new String(Files.readAllBytes(file.toPath()));

		// Load pattern call tuples from a file
		patternCallService.loadPatternCallsFromFile(content);

		// Perform queries
		PatternCall patternCall = patternCallService.getPatternCallById(42);
		System.out.println("Pattern Call with ID 42: "+ patternCall);

		List<PatternCall> patternCallsByName = patternCallService.getPatternCallsByName("myPattern");
		System.out.println("Pattern Calls with Name 'myPattern': ");
		patternCallsByName.stream().forEachOrdered(System.out::println);

		List<PatternCall> patternCallsByPath = patternCallService.getPatternCallsByPath("src/patterns/Functional.pat");
		System.out.println("Pattern Calls with Path 'src/patterns/Functional.pat': ");
		patternCallsByPath.stream().forEachOrdered(System.out::println);

		List<PatternCall> skippedPatternCalls = patternCallService.getSkippedPatternCalls();
		System.out.println("Skipped Pattern Calls: ");
		skippedPatternCalls.stream().forEachOrdered(System.out::println);

		List<PatternCall> calledPatternCalls = patternCallService.getCalledPatternCalls();
		System.out.println("Not Skipped Pattern Calls: ");
		calledPatternCalls.stream().forEachOrdered(System.out::println);


		Resource resourceOutput = new ClassPathResource("pattern_calls_output.txt");
		File outputFile = resourceOutput.getFile();

		// Dump queries to an output file
		patternCallService.dumpQueriesToFile(outputFile);
	}

}
