package controller;

import java.awt.EventQueue;

import view.MainFrame;
import model.core.Agent;
import model.core.Environment;
import model.core.population.PeopleChaste;
import model.core.population.People;
import model.core.population.PeopleType1;
import model.core.population.PeopleType2;
import model.core.water.Fish;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {
        // -------------- tp 2 population ----------------
        Environment city = new Environment(6, People.class);
        
        People people1 = new PeopleType1(0, 0, city, 1);
        People people2 = new PeopleType1(0, 1, city, 1);
        People people3 = new PeopleType1(2, 2, city, 1);
        People people4 = new PeopleType2(0, 3, city, 1);
        People people5 = new PeopleType2(0, 4, city, 1);
        People people6 = new PeopleType2(2, 5, city, 1);
        city.addAgent(people1);
        city.addAgent(people2);
        city.addAgent(people3);
        city.addAgent(people4);
        city.addAgent(people5);
        city.addAgent(people6);
        
        // initiate view
        MainFrame view = new MainFrame(city);
        // attach view as observer of the model
        city.attach(view);
        view.setVisible(true);
        
        float generalSatisfaction = 0;
        for (Agent a : city.agents) {
            System.out.println(a.getClass().getName());
            People p = (People) city.grid[a.getPosX()][a.posY];
            generalSatisfaction = generalSatisfaction + p.getSatisfaction();
            System.out.println("People sat' : "+p.getSatisfaction());
        }
        generalSatisfaction = generalSatisfaction / city.agents.size();
        System.out.println("GeneralSatisfaction : "+generalSatisfaction);

        
        /*
        // ---------------- tp 1 fish ------------------
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    // initiate model
                    WaterEnvironment water = new WaterEnvironment(60, Fish.class, 450, 250);
                    //WaterEnvironment water = new WaterEnvironment(10, Fish.class, 8, 8);
                    water.initiateGrid();

                    // initiate view
                    MainFrame view = new MainFrame(water);

                    // attach view as observer of the model
                    water.attach(view);
                    view.setVisible(true);
                    //Collections.shuffle(env.agents);                    

                    System.out.println("Number of agents : " + water.agents.size());
                    System.out.println("Size of the grid : " + water.grid.length + " x " + water.grid.length);
                    System.out.println(water.toString());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        */
        
        
    }
}
