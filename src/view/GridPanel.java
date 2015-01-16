package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.core.Agent;
import model.core.water.Shark;

public class GridPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    public JLabel[][] labelsGrid;
    
    public static JLabel shark;
    
    public static JLabel tuna;
    
    public static JLabel water;

    static ImageIcon iconShark = new ImageIcon("images/shark-icon.png");
    static ImageIcon iconFish = new ImageIcon("images/fish-icon.png");
    static ImageIcon iconWater = new ImageIcon("images/blue-water-icon.png");

    public GridPanel(Agent[][] grid) {
        this.labelsGrid = new JLabel[grid.length][grid.length];
        setLayout(new GridLayout(grid.length, grid.length));

        this.labelsGrid = new JLabel[grid.length][grid.length];

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                JLabel jLabel = new JLabel();
                //this.add(new JLabel("huu"));
                if (grid[row][column] != null) {
                    if (grid[row][column].getClass().equals(Shark.class)) {

                        jLabel = new JLabel();
                        jLabel.setIcon(iconShark);
                        this.add(jLabel);
                    } else {
                        jLabel = new JLabel();
                        jLabel.setIcon(iconFish);
                        this.add(jLabel);
                    }
                } else {
                    jLabel = new JLabel();
                    jLabel.setIcon(iconWater);
                    this.add(jLabel);
                }
                this.labelsGrid[row][column] = jLabel;

            }
        }  
    }

}
