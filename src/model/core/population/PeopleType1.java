package model.core.population;

import model.core.Environment;

public class PeopleType1 extends People {

    public PeopleType1(int posX, int posY, Environment environment,
            float minSatisfaction) {
        super(posX, posY, environment, minSatisfaction);
    }
    
    @Override
    public String toString() {
        return "P1";
    }

}
