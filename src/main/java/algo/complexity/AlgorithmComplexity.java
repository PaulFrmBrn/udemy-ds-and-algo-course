package algo.complexity;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AlgorithmComplexity {

    public String getDefinitionOfAlgorithmPerformance() {
        return getFromFile("DefinitionOfAlgorithmPerformance.md");
    }

    public String getOmegaNotationDefinition() {
        return getFromFile("OmegaNotationDefinition.md");
    }

    public String getBigONotationDefinition() {
        return getFromFile("BigONotationDefinition.md");
    }

    public String getThetaNotationDefinition() {
        return getFromFile("ThetaNotationDefinition.md");
    }

    public String getRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray() {
        return getFromFile("RuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray.md");
    }

    public String getReasoningForRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray() {
        return getFromFile("ReasoningForRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray.md");
    }

    public String getRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray() {
        return getFromFile("RuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray.md");
    }

    public String getReasoningForRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray() {
        return getFromFile("ReasoningForRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray.md");
    }

    public String getFromFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(getClass().getResource(fileName).toURI())));
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
