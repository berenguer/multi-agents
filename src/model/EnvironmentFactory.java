package model;

import model.core.Environment;
import model.core.population.People;
import model.core.population.PeopleType1;
import model.core.population.PeopleType2;
import model.core.water.Fish;
import model.core.water.Shark;
import model.core.water.Tuna;
import model.core.water.WaterEnvironment;

public class EnvironmentFactory {

    // positions are random
    public static Environment createAndInitializeCity(int size, int totalPeople1, int totalPeople2, float minSatisfaction) {
            Environment city = new Environment(size, People.class);
            int people1_count = totalPeople1;
            int people2_count = totalPeople2;

            // place agents into the grid
            while ((people1_count | people2_count) > 0) {
                // random = 0 place a fish or random = 1 place a shark
                int randomType = (int) Math.round(Math.random() * (1));

                People agent;
                int[] availablePosition = city.findEmptyPosition();
                int posX = availablePosition[0];
                int posY = availablePosition[1];

                // create a fish, put it the grid, and referenced it in the list of
                // agents
                if ((randomType == 0) && (people1_count > 0)) {
                    agent = new PeopleType1(posX, posY, city, minSatisfaction);
                    city.addAgent(agent);
                    people1_count--;
                }
                // create a shark, put it in the grid, and referenced it in the list
                // of agents
                else if ((randomType == 1) && (people2_count > 0)) {
                    agent = new PeopleType2(posX, posY, city, minSatisfaction);
                    city.addAgent(agent);
                    people2_count--;
                }
            }
            return city;
    }
    
    public static WaterEnvironment createAndInitializeWater(int size, int totalTunas, int totalSharks,
            int tunaBirthDelay, int sharkBirthDelay, int sharkDeathDelay) {
        
        WaterEnvironment water = new WaterEnvironment(size, Fish.class, totalTunas, totalSharks);
        int totalTunas_count = totalTunas;
        int totalSharks_count = totalSharks;

        // place agents into the grid
        while ((totalTunas_count | totalSharks_count) > 0) {
            // random = 0 place a fish or random = 1 place a shark
            int randomType = (int) Math.round(Math.random() * (1));

            Fish agent;
            int[] availablePosition = water.findEmptyPosition();
            int posX = availablePosition[0];
            int posY = availablePosition[1];

            // create a fish, put it the grid, and referenced it in the list of
            // agents
            if ((randomType == 0) && (totalTunas_count > 0)) {
                agent = new Tuna(posX, posY, 1, water);
                water.addAgent(agent);
                totalTunas_count--;
            }
            // create a shark, put it in the grid, and referenced it in the list
            // of agents
            else if ((randomType == 1) && (totalSharks_count > 0)) {
                agent = new Shark(posX, posY, 3, 3, water);
                water.addAgent(agent);
                totalSharks_count--;
            }
        }
        return water;
    }

}
