package assignment2;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static assignment2.GaussianElimination.gaussianElimination;


public class GaussianEliminationTest {
    @Rule
    public Timeout globalTimeout = Timeout.seconds(2);

    @Test
    public void matrix1_5P() {
        double[][] inputMatrix = {{1, 2, -1, 3}, {2, -1, 2, 6}, {1, -3, 3, 4}};
        double[][] solutionMatrix = {{2.0, -1.0, 2.0, 6.0}, {0.0, 2.5, -2.0, 0.0}, {0.0, 0.0, 0.0, 1.0}};
        gaussianElimination(inputMatrix);
        Assert.assertArrayEquals(solutionMatrix,inputMatrix);
    }

    @Test
    public void matrix2_5P() {
        double[][] inputMatrix = {{0,3},{2,0}};
        double[][] solutionMatrix = {{2.0, 0.0}, {0.0, 3.0}};
        gaussianElimination(inputMatrix);
        Assert.assertArrayEquals(solutionMatrix,inputMatrix);
    }

    @Test
    public void matrix3_5P() {
        double[][] inputMatrix = {{1}};
        double[][] solutionMatrix = {{1}};
        gaussianElimination(inputMatrix);
        Assert.assertArrayEquals(solutionMatrix,inputMatrix);
    }
}