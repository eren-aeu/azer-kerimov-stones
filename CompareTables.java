import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CompareTables {
    public static void main(String[] args) {
        /* 
        Scanner scanner = new Scanner(System.in);
        System.out.print("write the first file name : ");
        String firstFile = scanner.next();

        System.out.println();
        System.out.print("write the secınd file name  : ");
        String secondFile = scanner.next();

        compare(firstFile,secondFile);
        */
        //minCSV("minDeneme.csv", "dataDeneme.csv", "");
        
    }
    public static void compare(String f1, String f2){
        
        try{
            File file1 = new File(f1);
            File file2 = new File(f2);

            int sayaç = 1 ; // index starts from 1
            Scanner s1 = new Scanner(file1);
            Scanner s2 = new Scanner(file2);
            while (s1.hasNextInt() && s2.hasNextInt()){
                int line1 = s1.nextInt();
                int line2 = s2.nextInt();

                if (line1 != line2){
                    System.out.println(sayaç + ". position, values are: " + line1 + ", " + line2);
                }
                sayaç++;
            }
            sayaç --;
            System.out.println("Compared successfully, sayaç is " + sayaç);
        }catch(IOException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void minCSV(String f1, String f2, String newFileName){
        //takes two files and produces one other csv which is contains the minimum of each two.
        try {
            File file1 = new File(f1);
            File file2 = new File(f2);

            
            Scanner s1 = new Scanner(file1);
            Scanner s2 = new Scanner(file2);

            try (FileWriter fileWriter = new FileWriter(newFileName)){
                while (s1.hasNextInt() && s2.hasNextInt()){
                    int line1 = s1.nextInt();
                    int line2 = s2.nextInt();
                    int min = line1;
                    if (line1 > line2){
                        min = line2;
                    }
                fileWriter.append(Integer.toString(min));
                fileWriter.append("\n");
            }
            }
            catch(IOException e){ System.out.println(e.getMessage());
            }

            
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}
