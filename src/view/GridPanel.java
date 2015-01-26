package view;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Main;
import model.core.Agent;
import model.core.hunt.Hunter;
import model.core.hunt.Wall;
import model.core.population.PeopleType1;
import model.core.water.Shark;

public class GridPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3827701605430382079L;

    public JLabel[][] labelsGrid;
    
    public static JLabel shark;
    
    public static JLabel tuna;
    
    public static JLabel water;
    
    public static JLabel wall;

    static ImageIcon iconShark = new ImageIcon(Main.class.getResource("/img/shark-icon.png"));
    static ImageIcon iconFish = new ImageIcon(Main.class.getResource("/img/fish-icon.png"));
    static ImageIcon iconWater = new ImageIcon(Main.class.getResource("/img/blue-water-icon.png"));
    static ImageIcon iconWall = new ImageIcon(Main.class.getResource("/img/stone-icon.png"));

    public GridPanel(Agent[][] grid) {
        this.labelsGrid = new JLabel[grid.length][grid.length];
        setLayout(new GridLayout(grid.length, grid.length));

        this.labelsGrid = new JLabel[grid.length][grid.length];

        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                JLabel jLabel = new JLabel();
                if (grid[row][column] != null) {
                    if ((grid[row][column].getClass().equals(Shark.class) |
                            (grid[row][column].getClass().equals(PeopleType1.class) |
                                    (grid[row][column].getClass().equals(Hunter.class))))) {

                        jLabel = new JLabel();
                        jLabel.setIcon(iconShark);
                        this.add(jLabel);
                    } else if (grid[row][column].getClass().equals(Wall.class)) {
                        jLabel = new JLabel();
                        jLabel.setIcon(iconWall);
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
