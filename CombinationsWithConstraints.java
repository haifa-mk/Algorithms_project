import java.util.ArrayList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CombinationsWithConstraints {
    public static void main(String[] args) {
        int targetSum = 1000;
        int[] maxValues = {1000, 800, 500}; // Maximum values for x1, x2, and x3
        
        List<List<Integer>> result = findCombinations(targetSum, maxValues);
        
        // Print the combinations
        writeCombinationsToFile(result, "combination1s.txt");
    }

    public static List<List<Integer>> findCombinations(int targetSum, int[] maxValues) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(targetSum, maxValues, new ArrayList<>(), result);
        return result;
    }

    public static void generateCombinations(int targetSum, int[] maxValues, List<Integer> currentCombination, List<List<Integer>> result) {
        if (currentCombination.size() == maxValues.length) {
            int sum = 0;
            for (int num : currentCombination) {
                sum += num;
            }
            if (sum == targetSum) {
                result.add(new ArrayList<>(currentCombination));
            }
            return;
        }

        for (int i = 0; i <= maxValues[currentCombination.size()]; i++) {
            currentCombination.add(i);
            generateCombinations(targetSum, maxValues, currentCombination, result);
            currentCombination.remove(currentCombination.size() - 1);
        }
    }


    public static void writeCombinationsToFile(List<List<Integer>> combinations, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (List<Integer> combination : combinations) {
                writer.write(combination.toString());
                writer.newLine();
            }
            System.out.println("Combinations written to " + filename);
        } catch (IOException e) {
            System.err.println("Error writing combinations to file: " + e.getMessage());
        }
    }
}


