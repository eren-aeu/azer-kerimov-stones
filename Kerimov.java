import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.function.BiFunction;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Kerimov {

   // public static BiFunction<Integer, Double, Integer> numberOfWeightedSubsets;

    Random rastgele = new Random();

    double currentWeight = 0;
    double weightedNumber;
    int N ;
    double [] stones;
    ArrayList<double[]> powerSet;

    public Kerimov(int N, double weightedNumber){

        this.N = N;
        this.weightedNumber  = weightedNumber;
        stones = new double[N];
        /*
         * This approach seems to distribute the weights in such a way that 
         * the last stone will have a smaller weight compared to the other stones. 
         * If you want a more equal distribution of weights among the stones, 
         * you could consider using a different approach for generating the weights, 
         * such as dividing the remaining weight equally among the remaining stones 
         * or generating weights uniformly between 0 and 1 and then normalizing them to add up to 1.
         
        for (int i = 0; i < N - 1; i++){

            stones[i] = rastgele.nextDouble(0,1 - weight);
            weight += stones[i];
        }
        stones[N-1] = 1 - weight;
        */
        for (int i =0; i< N ; i++){
            double x =rastgele.nextDouble(0,1);   // to get small values such as 0.001 we use this method, just a trial now.
            //double x = rastgele.nextInt(100000);
            stones[i] = x;
            currentWeight += x;
        }

        //normalisation
        for (int i =0; i< N ; i++){
            stones[i] = stones[i] / currentWeight;
        }
        //powerSet = generatePSet(stones);
        powerSet = generatePowerSetCHAT(stones);
    }

    public void showStones(){
        String stoneString = "";
        for (double eleman: stones){
            stoneString += eleman + " ";
        }
        System.out.println(stoneString);
        System.out.println();
        System.out.println("SON TAŞ: " + stones[N-1]);

    }
    
/* 
    public ArrayList<double[]> generatePowerSet(double[] arr){
        ArrayList<double[]> powerSet = new ArrayList<>();
        if (arr.length == 0){powerSet.add(new double[0]);}
        else{   
            powerSet.add(arr);
            for (int i = 0; i < arr.length; i++){
                double[] tempArr = new double[arr.length-1];
                int tempIndex= 0;
                for (int t = 0; t < arr.length; t++){
                    if(i != t){
                        tempArr[tempIndex] = arr[t];
                        tempIndex++;
                    }
                }
                powerSet.addAll( generatePowerSet(tempArr));
            }
        }

        // so many recursive subset, remove them here
        boolean isThereRecursiveySubset = true;
        while(isThereRecursiveySubset){
            isThereRecursiveySubset = false;
            int recursiveIndex = -1;
            outerLoop:
            for( int i = 0; i < powerSet.size(); i++){
                for (int t = i +1; t < powerSet.size(); t++){
                    if(powerSet.get(i).equals(powerSet.get(t))){
                        isThereRecursiveySubset = true;
                        recursiveIndex = i;
                        break outerLoop;
                    }
                }
            }
            if (recursiveIndex > -1){
                System.out.println("TO BE REMOVED");
                for(double eleman: powerSet.get(recursiveIndex)){
                    System.out.println(eleman);
                }
                powerSet.remove(recursiveIndex);
                System.out.println("REMOVED");
                System.out.println("************************");
            }
        }
        return powerSet;
    }
    */
    public void showPowerSet() {
        System.out.println("size of p sset is: " + powerSet.size());
        System.out.println();
        for (double[] subset: powerSet){
            for(double eleman: subset){
                if (eleman != stones[stones.length -1]){
                    System.out.print(eleman +", ");
                }
                else{System.out.print(eleman + "."); }
            }
            System.out.println();
            System.out.println("---------------------------");
        }
    }
    public ArrayList<double[]> generatePSet(double[] arr){
        ArrayList<double[]> nonEmptyArrList = new ArrayList<>();

        if (arr.length == 0){
            double[] emptySet = new double[0];
            //ArrayList<double[]> emptyArrList = new ArrayList<>();
            //emptyArrList.add(emptySet);
            //return emptyArrList;
            nonEmptyArrList.add(emptySet);
        }

        else{
            for (int i = 0; i < arr.length; i++){
                //System.out.println("now in loop i: " + i + ", current lenght: " + arr.length);
                double currentElement = arr[i];
                //make an array from the rest
                double[] restOfArr = new double[arr.length - i - 1];
                for (int j = (i + 1); j < arr.length; j++){
                    restOfArr[j - i - 1] = arr[j];
                }
                ArrayList<double[]> pSetWithoutCurrentElement = new ArrayList<>();
                //ArrayList<double[]> pSetWithCurrentElement = new ArrayList<>();

                //System.out.println("recursive with lenght: " + restOfArr.length);
                pSetWithoutCurrentElement = generatePSet(restOfArr);
                

                for ( double[] subsetWithoutCurrent: pSetWithoutCurrentElement){
                    double[] subsetWithCurrent = new double[subsetWithoutCurrent.length + 1];
                    for ( int l = 0; l < subsetWithoutCurrent.length; l++){
                        subsetWithCurrent[l] = subsetWithoutCurrent[l];
                    }
                    subsetWithCurrent[subsetWithoutCurrent.length] = currentElement;
                    
                    nonEmptyArrList.add(subsetWithCurrent); //all subsets with current element is now added 
                }
            }
            //return nonEmptyArrList;
        }
        return nonEmptyArrList;
    }

    public ArrayList<double[]> generatePowerSetCHAT(double[] arr) {
        ArrayList<double[]> powerSet = new ArrayList<>();
    
        if (arr.length == 0) {
            double[] emptySet = new double[0];
            powerSet.add(emptySet);
        } 
        else {
            double[] restOfArr = new double[arr.length - 1];
            System.arraycopy(arr, 1, restOfArr, 0, arr.length - 1);
    
            ArrayList<double[]> subsetsWithoutCurrent = generatePowerSetCHAT(restOfArr);
    
            for (double[] subset : subsetsWithoutCurrent) {
                double[] subsetWithCurrent = new double[subset.length + 1];
                subsetWithCurrent[0] = arr[0];
                System.arraycopy(subset, 0, subsetWithCurrent, 1, subset.length);
                powerSet.add(subsetWithCurrent);
            }
    
            powerSet.addAll(subsetsWithoutCurrent);
        }
    
        return powerSet;
    }
    
    public int numberOfWeightedSubsets(){
        int count = 0;
        for (double[] subset: powerSet){
            double totalOfSubset = 0;
            for (double stone: subset){
                totalOfSubset += stone;
            }
            if (totalOfSubset >= weightedNumber){
                count ++;
            }
        }
        return count;
    }
    public int minNumberOfDesiredSubsets(){
        int min =(int) Math.pow(2, N);
        int current;
        for (int i = 0; i < 1000; i++){
            current = numberOfWeightedSubsets();
            if (current < min){
                min = current;
            }
        }
        return min;
    }
    
 

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        JTextField numberField = new JTextField(10);
        JTextField weightedNumberField_numerator = new JTextField(5);
        JTextField weightedNumberField_denumerator = new JTextField(5);
        JTextField numberOfTrials = new JTextField(5);
        JLabel isDone = new JLabel("Ready to calculate");


        JButton calculateButton = new JButton("Calculate");
        JTextArea resultArea = new JTextArea(2, 10);

        calculateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    isDone.setText("Calculating...");
                    int N = Integer.parseInt(numberField.getText());
                    double weightedNumberNumerator = Double.parseDouble(weightedNumberField_numerator.getText());
                    double weightedNumberDenumerator = Double.parseDouble(weightedNumberField_denumerator.getText());

                    int minNumberOfDesiredSubsets = N * N; //just to get a big number
                    int countOfTrials = Integer.parseInt(numberOfTrials.getText());

                    for (int i = 0; i < countOfTrials; i++){
                        Kerimov kerimov = new Kerimov(N, weightedNumberNumerator / weightedNumberDenumerator);
                        int desiredSubsetNum = kerimov.numberOfWeightedSubsets();
                        if (desiredSubsetNum < minNumberOfDesiredSubsets){
                            minNumberOfDesiredSubsets = desiredSubsetNum;
                        }
                    }
                    isDone.setText("Done.");
                    resultArea.setText("Min number of desired subsets: " + minNumberOfDesiredSubsets);
                } catch (NumberFormatException ex) {
                    resultArea.setText("Invalid input! Please enter valid numbers.");
                }
            }
        });

        panel.add(new JLabel("Number of Stones:"));
        panel.add(numberField);
        panel.add(new JLabel("Weighted Number:"));
        panel.add(weightedNumberField_numerator);
        panel.add(new JLabel(" / "));
        panel.add(weightedNumberField_denumerator);
        panel.add(new JLabel("Number of Trials"));
        panel.add(numberOfTrials);
        panel.add(calculateButton);
        panel.add(resultArea);
        panel.add(isDone);

        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
}

    
