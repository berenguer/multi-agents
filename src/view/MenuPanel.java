package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

import model.core.Environment;

public class MenuPanel extends JPanel {
    
    public Environment environnement;
    
    public JButton runButton;
    public JButton pauseButton;
    public JButton nextButton;
    
    public Timer timer;

    public MenuPanel(Environment environnement) {
        super();
        this.environnement = environnement;
        setLayout(new GridLayout(1, 4));
        
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
        ImageIcon iconPause = new ImageIcon("images/pause.png");
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
        ImageIcon iconPlay = new ImageIcon("images/play.png");
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
        ImageIcon iconNext = new ImageIcon("images/next.png");
        nextButton.setIcon(iconNext);
        nextButton.addActionListener(new ActionListener() {     
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuPanel.this.environnement.doIt();
            }
        });
        add(nextButton);

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
        add(slider);
        
    }

}
