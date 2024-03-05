
import java.awt.Graphics;
import java.util.function.BiFunction;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * DisplayFunction
 */
public class DisplayFunction extends JFrame{

    JPanel infoPanel;
    GraphPanelEREN graphPanel;
    final int WIDTH_OF_FRAME = 1000;
    final int HEIGHT_OF_FRAME = 1000;
    JTextField numberOfStonesTx;
    JTextField weightedNumberTx;

   // BiFunction<Integer, Double, Integer> function;


    public DisplayFunction(){
        infoPanel = new JPanel();
        graphPanel = new GraphPanelEREN();
        numberOfStonesTx = new JTextField(10);
      

        infoPanel.add(new JLabel("Number of stones: "));
        infoPanel.add(numberOfStonesTx);

        add(infoPanel);
        //add(graphPanel);
        setSize(WIDTH_OF_FRAME, HEIGHT_OF_FRAME);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    

    //public static int applyFunction(BiFunction<Integer, Double, Integer> function, int numberOfStones, double weightedNumber) {
        //return function.apply(numberOfStones, weightedNumber);
    
    public static void main(String[] args) {
        DisplayFunction df = new DisplayFunction();
    }
}