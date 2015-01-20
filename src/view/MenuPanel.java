package view;

import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import controller.Main;
import model.core.Environment;

public class MenuPanel extends JPanel {
    
    /**
     * 
     */
    private static final long serialVersionUID = 5776134343830612784L;

    public Environment environnement;
    
    public JButton runButton;
    public JButton pauseButton;
    public JButton nextButton;
    
    public JSlider slider;
    
    public Timer timer;
    public ActionListener taskPerformer;

    public MenuPanel(Environment environnement) {
        super();
        this.environnement = environnement;

        GridBagLayout gbLayout = new GridBagLayout();
        setLayout(gbLayout);
        
        // -------------------- Timer -----------------------
        int delay = 100; // milliseconds
        ActionListener taskPerformer = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                MenuPanel.this.environnement.doIt();
            }
        };
        this.timer = new Timer(delay, taskPerformer);
        
        // ---------------------- pause ---------------------
        pauseButton = new JButton();
        ImageIcon iconPause = new ImageIcon(Main.class.getResource("/img/pause.png"));
        pauseButton.setIcon(iconPause);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.this.pauseButton.setEnabled(false);
                MenuPanel.this.runButton.setEnabled(true);
                MenuPanel.this.timer.stop();
            }
        });
        
        add(pauseButton);
        
        // ---------------------- play ----------------------
        runButton = new JButton();
        ImageIcon iconPlay = new ImageIcon(Main.class.getResource("/img/play.png"));
        runButton.setIcon(iconPlay);
        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {            
                MenuPanel.this.runButton.setEnabled(false);
                MenuPanel.this.pauseButton.setEnabled(true);
                MenuPanel.this.timer.start();
            }
        });
        add(runButton);

        // ---------------------- next ----------------------
        nextButton = new JButton();
        ImageIcon iconNext = new ImageIcon(Main.class.getResource("/img/next.png"));
        nextButton.setIcon(iconNext);
        nextButton.addActionListener(new ActionListener() {     
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.this.environnement.doIt();
            }
        });
        add(nextButton);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 6, 2);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(6);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setLabelTable(slider.createStandardLabels(3));
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                MenuPanel.this.timer.setDelay(slider.getValue() * 100);
                System.out.println("delais : "+MenuPanel.this.timer.getDelay());
            }
        });
        add(slider);
        
    }

}
