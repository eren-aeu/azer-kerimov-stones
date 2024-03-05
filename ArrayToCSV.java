
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;


public class ArrayToCSV {
    public static void main(String[] args) {
        
        int[] numberOfSubsetsGiven_7Stones = new int[1000]; // 7 stones is given, weighted number varies
        double weightedNumber = 0.0;
        for (int i = 0; i < numberOfSubsetsGiven_7Stones.length; i++){
            int min = (int) Math.pow(2, 7);
            int current;
            for (int k = 0; k < 1000000; k++){
                Kerimov kerimov = new Kerimov(7, weightedNumber);
                current = kerimov.numberOfWeightedSubsets();
                if (current < min){
                    min = current;
                }
            }
            numberOfSubsetsGiven_7Stones[i] = min;
            weightedNumber += 0.001;
        }

        String csvFilePath = "dataDenemeDouble100000.csv"; // Path to save the CSV file

        try (FileWriter writer = new FileWriter(csvFilePath)) {
            for (int i = 0; i < numberOfSubsetsGiven_7Stones.length; i++) {
                writer.append(Integer.toString(numberOfSubsetsGiven_7Stones[i]));
                writer.append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
