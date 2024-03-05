import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GraphPanelEREN extends JPanel {
    int width;
    int height;
    int originX;
    int originY;
    int endPointX;
    int endPointY;
    int scaleSpacing;

    public GraphPanelEREN() {
        // Set fixed size for the panel
        width = 500;
        height = 500;
        setPreferredSize(new Dimension(width, height));

        // Set origin point
        originX = 25;
        originY = height - 25;

        // Set end point related to origin point
        endPointX = width - originX;
        endPointY = height - originY;

        // Set scale spacing
        scaleSpacing = 20;
    }

    protected void paintComponent(Graphics grf) {
        super.paintComponent(grf);
        Graphics2D graphic2D = (Graphics2D) grf;

        graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphic2D.setPaint(Color.BLACK);

        // Draw axes using the instance variables
        graphic2D.drawLine(originX, originY, originX, endPointY);
        graphic2D.drawLine(originX, originY, endPointX, originY);

        graphic2D.setFont(new Font("Arial", Font.PLAIN, 12));

        //draw point on X axis
        for (int x = originX + scaleSpacing; x <= endPointX; x += scaleSpacing) {
            int label = (x - originX) / scaleSpacing;
            graphic2D.drawString(Integer.toString(label), x - 5, originY + 15);
            graphic2D.fillOval(x - 5, originY - 3, 6, 6);
        }
        //draw point on Y axis
        for (int y = originY - scaleSpacing; y >= endPointY; y -= scaleSpacing) {
            int label = (y - originY) / scaleSpacing;
            graphic2D.drawString(Integer.toString(-5 * label), originX - 20, y+7);
            graphic2D.fillOval(originX - 3, y , 6, 6);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GraphPanelEREN graphPanel = new GraphPanelEREN();
        frame.add(graphPanel);

        frame.pack(); // Pack the frame to fit the preferred size of the panel

        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
