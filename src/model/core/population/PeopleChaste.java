package model.core.population;

import model.core.Agent;
import model.core.Environment;

public class PeopleChaste extends Agent {

	/**
	 * is the satisfaction for agent
	 */
    public float satisfaction;
    /**
     * the total
     */
    int nbVoisinTotal ;
    /**
     * the number of neighboor with the same Type
     */
    int nbVoisinMemeType ;
    /**
     * the number of neighboor with different Type
     */
    int nbVoisinDifferentType ;
    /**
     * for know the type
     */
    public int type;

    public PeopleChaste(int posX, int posY, Environment environment, int type) {
        super(posX, posY, environment);
        // just to initate
        this.satisfaction = 0;
        this.type = type;
        nbVoisinTotal = 0;
         nbVoisinMemeType = 0;
        nbVoisinDifferentType = 0;
    }

    @Override
	public void action() {
		//
	}

    /**
     * to update the value of satisfaction
     */
    public void calculateSatisfaction() {
    	// on remet a 0 pour ne pas fausser les calculs
    	nbVoisinTotal = 0;
         nbVoisinMemeType = 0;
        nbVoisinDifferentType = 0;
        int posX = this.getPosX();
        int posY = this.getPosY();
        if (posX == 0) {
            if (posY == 0) { // x and y =0;
                satisfaction = getSatisfactionForPosx0PosY0();
            } else {
                if (posY == environment.grid.length) {
                	//x=0 y = Max
                    satisfaction=getSatisfactionForPosx0PosYMax();
                }
                //x=0;Y=Lambda
               satisfaction= getSatisfactionForPosx0PosYLambda();
            }

        } else {
            // x !=0
            if (posX == environment.grid.length) {

                if (posY == environment.grid.length) {
                    // x et y = max
                	satisfaction=getSatisfactionForPosxMaxPosYmax();
                } else {

                    if (posY == 0) { // x = max and y =0;
                    	satisfaction=getSatisfactionForPosxMaxPosY0();
                    } else {
                    	satisfaction=getSatisfactionForPosxMaxPosYLambda();
                        // x = max y standard
                    }

                }

            } else {
                if (posY == environment.grid.length) {
                	satisfaction=getSatisfactionForPosxLambdaPosYMax();
                    // x standard y = max
                } else {
                    // x and y standard
                	satisfaction=getSatisfactionForPosxLambdaPosYLambda();
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


        // voisin de droite
        MajValueAgent(getVoisinDroite());
        // voisin du bas
        MajValueAgent(getVoisinBas());
        // voisin diagonal bas droite
        MajValueAgent(getVoisinBasDroite());

        return satisfactionForOneGuy();
    }

    /**
     * calculate for posX=0 posY=max
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosx0PosYMax() {
        // voisin de droite
        MajValueAgent(getVoisinDroite());
        // voisin du haut
        MajValueAgent(getVoisinHaut());
        // voisin diagonal haut droit
        MajValueAgent(getVoisinHautDroite());
        return satisfactionForOneGuy();
    }

    /**
     * calculate satisfaction for x =0 and y lambda
     * @return
     */
    public float getSatisfactionForPosx0PosYLambda(){
         // voisin de droite
         MajValueAgent(getVoisinDroite());
         // voisin du bas
         MajValueAgent(getVoisinBas());
         // voisin diagonal bas droite
         MajValueAgent(getVoisinBasDroite());
         // voisin du haut
         MajValueAgent(getVoisinHaut());
         // voisin diagonal haut droit
         MajValueAgent(getVoisinHautDroite());
         return satisfactionForOneGuy();
    }

    /**
     * calculate for posX=max posY=max
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosxMaxPosYmax()
    {
        // voisin du haut
    	 MajValueAgent(getVoisinHaut());
        //voisinGauche
        MajValueAgent(getVoisinGauche());
        //voisin hautGauche
        MajValueAgent(getVoisinHautGauche());
        return satisfactionForOneGuy();
    }

    /**
     * calculate for posX=max posY=0
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosxMaxPosY0()
    {
        //voisinGauche
        MajValueAgent(getVoisinGauche());
        //voisin BasGauche
        MajValueAgent(getVoisinBasGauche());
        //voisinBas
        MajValueAgent(getVoisinBas());
        return satisfactionForOneGuy();
    }


    /**
     * calculate for posX=max posY=Lambda
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosxMaxPosYLambda()
    {
    	 //voisinGauche
        MajValueAgent(getVoisinGauche());
        //voisin BasGauche
        MajValueAgent(getVoisinBasGauche());
        //voisinBas
        MajValueAgent(getVoisinBas());
        // voisin diagonal haut gauche
        MajValueAgent(getVoisinHautGauche());
        // voisin du haut
        MajValueAgent(getVoisinHaut());

        return satisfactionForOneGuy();
    }

    /**
     * calculate for posX=Lambda posY=Max
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosxLambdaPosYMax()
    {
    	 //voisinGauche
        MajValueAgent(getVoisinGauche());
        // voisin diagonal haut gauche
        MajValueAgent(getVoisinHautGauche());
        // voisin du haut
        MajValueAgent(getVoisinHaut());
        // voisin de droite
        MajValueAgent(getVoisinDroite());
        // voisin diagonal bas droite
        MajValueAgent(getVoisinBasDroite());

        return satisfactionForOneGuy();
    }

    /**
     * calculate for posX=lambda posY=lambda
     *
     * @return the satisfaction for this guy
     */
    public float getSatisfactionForPosxLambdaPosYLambda()
    {
    	 //voisinGauche
        MajValueAgent(getVoisinGauche());
        //voisin BasGauche
        MajValueAgent(getVoisinBasGauche());
        //voisinBas
        MajValueAgent(getVoisinBas());
        // voisin de droite
        MajValueAgent(getVoisinDroite());
        // voisin diagonal bas droite
        MajValueAgent(getVoisinBasDroite());
        // voisin du haut
        MajValueAgent(getVoisinHaut());
        // voisin diagonal haut droit
        MajValueAgent(getVoisinHautDroite());
     // voisin diagonal haut gauche
        MajValueAgent(getVoisinHautGauche());

        return satisfactionForOneGuy();
    }


    /**
     * Renvoie le voisin du haut
     * @return null si aucun voisin , sinon le voisin
     */
    public PeopleChaste getVoisinHaut()
    {
    	return (PeopleChaste) this.environment.grid[posX][posY- 1];
    }
    /**
     * retourne le voisin de droite
     * @return null si aucun voisin , sinon le voisin
     */
    public PeopleChaste getVoisinDroite()
    {
    	return (PeopleChaste) this.environment.grid[posX+1][posY];
    }
    /**
     * renvoie le voisin en haut a droite
     * @return null si aucun voisin , sinon le voisin
     */
    public PeopleChaste getVoisinHautDroite()
    {
    	return (PeopleChaste) this.environment.grid[posX+1][posY-1];
    }
    /**
     * renvoie le voisin en bas a droite
     * @return null si aucun voisin , sinon le voisin
     */
    public PeopleChaste getVoisinBasDroite()
    {
    	return (PeopleChaste) this.environment.grid[posX+1][posY+1];
    }
    /**
     * renvoie le voisin du bas
     * @return null si aucun voisin , sinon le voisin
     */
    public PeopleChaste getVoisinBas()
    {
    	return (PeopleChaste) this.environment.grid[posX][posY+1];
    }

    /**
     * renvoie le voisin en bas a gauche
     * @return
     */
    public PeopleChaste getVoisinBasGauche()
    {
    	return (PeopleChaste) this.environment.grid[posX-1][posY+1];
    }

    /**
     * renvoie le voisin s a gauche
     * @return
     */
    public PeopleChaste getVoisinGauche()
    {
    	return (PeopleChaste) this.environment.grid[posX-1][posY];
    }

    /**
     * renvoie le voisin en haut a gauche
     * @return
     */
    public PeopleChaste getVoisinHautGauche()
    {
    	return (PeopleChaste) this.environment.grid[posX-1][posY-1];
    }

    /**
     * Calcul la satisfaction pour un type
     *
     * @param nbVoisinTotal
     * @param nbVoisinMemeType
     * @param nbVoisinDifferentType
     * @return satisfaction for one guy
     */
    public float satisfactionForOneGuy() {
        if (nbVoisinTotal == 0) {
            return 0;
        } else {
            return nbVoisinMemeType / nbVoisinTotal;
        }

    }
    /**
     * Change nbVoisinTotal nbVoisinMemeType, nbVoisinDifferentType with using agentVoisin
     * @param agentVoisin
     */
    public void MajValueAgent(PeopleChaste agentVoisin)
    {
        if (agentVoisin!= null) {
            this.nbVoisinTotal++;
            if (agentVoisin.type == this.type) {
                this.nbVoisinMemeType++;
            }
        }
    }


}
