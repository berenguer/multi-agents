package model.core.population;

import model.core.Environment;

public class PeopleType2 extends People {

    public PeopleType2(int posX, int posY, Environment environment,
            float minSatisfaction) {
        super(posX, posY, environment, minSatisfaction);
    }
    
    @Override
    public String toString() {
        return "P2";
    }

}
