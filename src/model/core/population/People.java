package model.core.population;

import model.core.Agent;
import model.core.Environment;

public class People extends Agent {

    public float satisfaction;

    /**
     * 
     */
    public int type;// 0 ou 1 pour chaque type

    public People(int posX, int posY, Environment environment, int type) {
        super(posX, posY, environment);
        // just to initate
        this.satisfaction = 0;
        this.type = type;
    }

    @Override
	public void action() {
		// 
	}

    /**
     * to update the value of satisfaction
     */
    public void calculateSatisfaction() {
        int posX = this.getPosX();
        int posY = this.getPosY();
        if (posX == 0) {
            if (posY == 0) { // x and y =0;
                satisfaction = getSatisfactionForPosx0PosY0();

            } else {
                if (posY == environment.grid.length) {
                    // x=0 , y=max

                }
                // only x=0 y = standard
            }

        } else {
            // x !=0
            if (posX == environment.grid.length) {

                if (posY == environment.grid.length) {
                    // x et y = max
                } else {

                    if (posY == 0) { // x = max and y =0;

                    } else {
                        // x = max y standard
                    }

                }

            } else {
                if (posY == environment.grid.length) {
                    // x standard y = max
                } else {
                    // x and y standard
                }

            }

        }

    }

    /**
     * calculate for posX=0 posY=0
     * 
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosx0PosY0() {
        int nbVoisinTotal = 0;
        int nbVoisinMemeType = 0;
        int nbVoisinDifferentType = 0;
        People agentVoisin;
        // voisin de droite
        if (this.environment.grid[1][0] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[1][0];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }
        // voisin du bas
        if (this.environment.grid[0][1] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[0][1];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }
        // voisin diagonal bas droite
        if (this.environment.grid[1][1] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[1][1];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }

        return satisfactionForOneGuy(nbVoisinTotal, nbVoisinMemeType,
                nbVoisinDifferentType);
    }

    /**
     * calculate for posX=0 posY=max
     * 
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosx0PosYMax() {
        int nbVoisinTotal = 0;
        int nbVoisinMemeType = 0;
        int nbVoisinDifferentType = 0;
        People agentVoisin;
        // voisin de droite
        if (this.environment.grid[1][this.environment.grid.length] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[1][this.environment.grid.length];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }
        // voisin du haut
        if (this.environment.grid[0][this.environment.grid.length - 1] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[0][this.environment.grid.length - 1];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }
        // voisin diagonal haut droit
        if (this.environment.grid[1][this.environment.grid.length - 1] != null) {
            nbVoisinTotal++;
            agentVoisin = (People) this.environment.grid[1][this.environment.grid.length - 1];
            if (agentVoisin.type == this.type) {
                nbVoisinMemeType++;
            } else {
                nbVoisinDifferentType++;
            }
        }

        return satisfactionForOneGuy(nbVoisinTotal, nbVoisinMemeType,
                nbVoisinDifferentType);
    }

    /**
     * Calcul la satisfaction pour un type
     * 
     * @param nbVoisinTotal
     * @param nbVoisinMemeType
     * @param nbVoisinDifferentType
     * @return satisfaction for one guy
     */
    public float satisfactionForOneGuy(int nbVoisinTotal, int nbVoisinMemeType,
            int nbVoisinDifferentType) {
        if (nbVoisinTotal == 0) {
            return 0;
        } else {
            return (nbVoisinMemeType + nbVoisinDifferentType) / nbVoisinTotal;
        }

    }

}
