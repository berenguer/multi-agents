package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.Observer;
import model.core.Agent;
import model.core.Environment;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public Environment environnement;
    
    public Agent[][] grid;
    
    public JPanel gridPanel;
    
    public JButton runButton;
    
    public JButton nextButton;

    public MainFrame(Environment environnement) {       
        // private variables
        this.environnement = environnement;
        setTitle("Multi-agents");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // set layout
        GridBagLayout gbLayout = new GridBagLayout();
        getContentPane().setLayout(gbLayout);
        GridBagConstraints c = new GridBagConstraints();

        this.gridPanel = new GridPanel(this.environnement.getGrid());
        c.gridx = 0;
        c.gridy = 1;
        add(this.gridPanel, c);  
        JButton runButton = new JButton("Run");
        JButton nextButton = new JButton("Next");

        runButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.environnement.run();
            }
        });
        
        nextButton.addActionListener(new ActionListener() {
                        
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.environnement.doIt();
            }
        });
        
        c.gridx = 2;
        c.gridy = 1;
        this.add(runButton, c);
        c.gridx = 1;
        c.gridy = 1;
        this.add(nextButton, c);
        
        // validate container and subcomponents
        validate();
        pack();
        setVisible(true);
    }
    // listeners
    //...
    @Override
    public void update() {
        remove(this.gridPanel);
        // to improve : refresh only one JLabel        
        this.gridPanel = new GridPanel(this.environnement.getGrid());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        add(this.gridPanel, c);
        validate();
    }
}
