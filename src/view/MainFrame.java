package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import view.Observer;
import model.core.Agent;
import model.core.Environment;

public class MainFrame extends JFrame implements Observer {

    private static final long serialVersionUID = 1L;

    public Environment environnement;
    
    public Agent[][] grid;
    
    public JPanel gridPanel;
    
    public JButton runButton;
    public JButton pauseButton;
    public JButton nextButton;

    public MainFrame(Environment environnement) {
        this.environnement = environnement;
        setTitle("Multi-agents");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set layout
        GridBagLayout gbLayout = new GridBagLayout();
        getContentPane().setLayout(gbLayout);
        GridBagConstraints c = new GridBagConstraints();
        
        // internal padding
        c.ipadx = 0;
        c.ipady = 0;
        // --------------------- menu -----------------------
        MenuPanel menu = new MenuPanel(this.environnement);
        c.gridx = 0;
        c.gridy = 0;
        menu.setPreferredSize(new Dimension(320, 30));
        this.add(menu, c);
        
        // --------------------- grid -----------------------
        this.gridPanel = new GridPanel(this.environnement.getGrid());
        c.gridx = 0;
        c.gridy = 1;
        add(this.gridPanel, c);
        
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
