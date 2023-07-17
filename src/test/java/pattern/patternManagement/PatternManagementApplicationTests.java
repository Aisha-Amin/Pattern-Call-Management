package pattern.patternManagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PatternManagementApplicationTests {

	private PatternCallService patternCallService;

	@BeforeEach
	public void setUp() {
		patternCallService = new PatternCallService();
	}

	@Test
	@DisplayName("Pattern Calls From File: Load Valid File Content and test data")
	public void testLoadPatternCallsFromFile() {
		String fileContent = "1,Pattern 1, file1.pat, true\n" +
				"2, Pattern 2, file2.pat, false\n" +
				"3, Pattern 3, file3.pat, true";
		patternCallService.loadPatternCallsFromFile(fileContent);

		List<PatternCall> patternCalls = patternCallService.getPatternCallsByPath("file1.pat");
		assertEquals(1, patternCalls.size());
		assertEquals("Pattern 1", patternCalls.get(0).getName());
	}

	@Test
	@DisplayName("Get Pattern Call By Id: Load Valid File Content and test data with Call by Id")
	public void testGetPatternCallById() {
		PatternCall patternCall = new PatternCall(42, "Pattern 42", "file42.pat", true);
		patternCallService.loadPatternCallsFromFile("42, Pattern 42, file42.pat, true");
		PatternCall result = patternCallService.getPatternCallById(42);
		assertEquals(42, result.getId());
	}

	@Test
	@DisplayName("Get Pattern Call By Invalid Id: Load File Content and test data with Call by Id which doesnot exists")
	public void testGetPatternCallByIdReturnsNull() {
		patternCallService.loadPatternCallsFromFile("1, Pattern 1, file1.pat, true");
		PatternCall result = patternCallService.getPatternCallById(42);
		assertNull(result);
	}

	@Test
	@DisplayName("Get Pattern Calls By Name: Load Valid File Content and test data with Call by Name")
	public void testGetPatternCallsByName() {
		patternCallService.loadPatternCallsFromFile(
				"1, Pattern 1, file1.pat, true\n" +
						"2, Pattern 2, file2.pat, true\n" +
						"3, Pattern 1, file3.pat, false"
		);
		List<PatternCall> result = patternCallService.getPatternCallsByName("Pattern 1");
		assertEquals(2, result.size());
		assertEquals("file1.pat", result.get(0).getPatternFile());
		assertEquals("file3.pat", result.get(1).getPatternFile());
	}

	@Test
	@DisplayName("Get Pattern Calls By Name Returns EmptyList: Load Valid File Content and test data with Call by Name which doesnot exists")
	public void testGetPatternCallsByNameReturnsEmptyList() {
		patternCallService.loadPatternCallsFromFile(
				"1, Pattern 1, file1.pat, true\n" +
						"2, Pattern 2, file2.pat, true\n" +
						"3, Pattern 1, file3.pat, false"
		);
		List<PatternCall> result = patternCallService.getPatternCallsByName("Pattern 3");
		assertTrue(result.isEmpty());
	}

	@Test
	@DisplayName("Test Dump Queries: Load Valid File Content and write the data on the output file")
	public void testDumpQueriesToFile() throws IOException {
		File outputFile = new File("pattern_calls_output_test.txt");
		patternCallService.loadPatternCallsFromFile("1, 'Pattern 1', 'file1.pat', true");
		patternCallService.dumpQueriesToFile(outputFile);

		assertTrue(outputFile.exists());
		assertTrue(outputFile.isFile());
		assertTrue(outputFile.length() > 0);
	}


	@Test
	void contextLoads() {
	}

}
