import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class AlgorithmComplexityTest {

    AlgorithmComplexity sut = new AlgorithmComplexity();

    @Test
    public void shouldReturnOmegaNotationDefinition() {
        assertFalse(sut.getOmegaNotationDefinition().isBlank());
    }

    @Test
    public void shouldReturnBigONotationDefinition() {
        assertFalse(sut.getBigONotationDefinition().isBlank());
    }

    @Test
    public void shouldReturnThetaNotationDefinition() {
        assertFalse(sut.getThetaNotationDefinition().isBlank());
    }

    @Test
    public void shouldReturnDefinitionOfAlgorithmPerformance() {
        assertFalse(sut.getDefinitionOfAlgorithmPerformance().isBlank());
    }


    @Test
    public void shouldReturnRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray() {
        assertEquals("O(n)", sut.getRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray());
    }

    @Test
    public void shouldReturnReasoningForRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray() {
        assertFalse(sut.getReasoningForRuntimeComplexityInBigONotationOfIterativeSearchInNonSortedArray().isBlank());
    }

    @Test
    public void shouldReturnRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray() {
        assertEquals("O(log2(n))", sut.getRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray());
    }

    @Test
    public void shouldReturnReasoningForRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray() {
        assertFalse(sut.getReasoningForRuntimeComplexityInBigONotationOfRecursiveBinarySearchInSortedArray().isBlank());
    }

}
