package pattern.patternManagement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
/**
 * Represents a pattern call entity.
 */
@AllArgsConstructor
@Getter
@ToString
public class PatternCall {
    private int id;
    private String name;
    private String patternFile;
    private boolean called;
}
