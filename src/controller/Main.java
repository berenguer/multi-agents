package controller;

import view.MainFrame;
import model.EnvironmentFactory;
import model.core.Environment;
import model.core.water.WaterEnvironment;

public class Main {
    
    public static void playCity() {
        
    }

    public static void main(String[] args) {
        if (args.length >= 1) {
            System.out.println(args[0]);
            if (args[0].equals("--water")) {
                // ---------------- tp 1 fish ------------------
                //WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(10, 8, 8, 1, 3, 3);
                WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(40, 200, 200, 1, 3, 3);
                MainFrame view = new MainFrame(water);
                // attach view as observer of the model
                water.attach(view);
                view.setVisible(true);
                System.out.println("Number of agents : " + water.agents.size());
                System.out.println("Size of the grid : " + water.grid.length + " x " + water.grid.length);
                System.out.println(water.toString());
            } else if (args[0].equals("--city")) {
                // -------------- tp 2 population ----------------
                Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);
                //Environment city = EnvironmentFactory.createAndInitializeCity(30, 50, 50, 0.6f);

                // initiate view
                MainFrame view = new MainFrame(city);
                // attach view as observer of the model
                city.attach(view);
                view.setVisible(true);
            }
        }
        
        else {
            System.out.println("Select environment with argument :\n--water\nor\n--city");
        }
    }
}
