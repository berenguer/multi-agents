package controller;

import view.MainFrame;
import model.EnvironmentFactory;
import model.core.Environment;
import model.core.hunt.HuntEnvironment;
import model.core.water.WaterEnvironment;

public class Main {

    public static void main(String[] args) {
        int size = 30;
        int nbType1 = 140;
        int nbType2 = 140;
        
        if (args.length >= 1) {
            // ---------------- tp 1 fish ------------------
            if (args[0].equals("--water")) {
                int tunaBirth = 1;
                int sharkBirth = 3;
                int sharkDeath = 3;
                if (args.length == 7) {
                    size = Integer.parseInt(args[1]);
                    nbType1 = Integer.parseInt(args[2]);
                    nbType2 = Integer.parseInt(args[3]);
                    tunaBirth = Integer.parseInt(args[4]);
                    sharkBirth = Integer.parseInt(args[5]);
                    sharkDeath = Integer.parseInt(args[5]);
                }
                //WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(10, 8, 8, 1, 3, 3);
                //WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(40, 200, 200, 1, 3, 3);
                WaterEnvironment water = EnvironmentFactory.createAndInitializeWater(size, nbType1, nbType2, tunaBirth,
                        sharkBirth, sharkDeath);
                MainFrame view = new MainFrame(water);
                // attach view as observer of the model
                water.attach(view);
                view.setVisible(true);
                System.out.println("Number of agents : " + water.agents.size());
                System.out.println("Size of the grid : " + water.grid.length + " x " + water.grid.length);
                //System.out.println(water.toString());
            } else if (args[0].equals("--city")) {
                // -------------- tp 2 population ----------------
                float satisfaction = 0.6f;
                if (args.length == 5) {
                    size = Integer.parseInt(args[1]);
                    nbType1 = Integer.parseInt(args[2]);
                    nbType2 = Integer.parseInt(args[3]);
                    satisfaction = Float.parseFloat(args[4]);
                }
                //Environment city = EnvironmentFactory.createAndInitializeCity(50, 800, 800, 0.7f);
                Environment city = EnvironmentFactory.createAndInitializeCity(size, nbType1, nbType2, satisfaction);
                // initiate view
                MainFrame view = new MainFrame(city);
                // attach view as observer of the model
                city.attach(view);
                view.setVisible(true);
            }
            
            else if (args[0].equals("--hunt")) {
                System.out.println("hunt!!!");
                HuntEnvironment env = EnvironmentFactory.createAndInitializeHunt(size, nbType1, nbType2);
                // initiate view
                MainFrame view = new MainFrame(env);
                // attach view as observer of the model
                env.attach(view);
                view.setVisible(true);
            }
            else {
                System.out.println("Select environment with argument :\n"
                        + "--water (int)size (int)type1 occurences (int)type2 occurences (int)Tuna birth delay "
                        + "(int)Shark birth delay (int)Shark death delay\n"
                        + "\texample: --water 40 200 200 1 3 3\n"
                        + "\n"
                        + "--city (int)size (int)type1 occurences (int)type2 occurences (float)min satisfaction\n"
                        + "\texample: --city 20 90 90 0.7");
            }
        }
    }
}
