package model.core.hunt;

import java.util.ArrayList;

import model.core.Agent;
import model.core.Environment;

public class Prey extends Agent {

    public Prey(int posX, int posY, Environment environment) {
        super(posX, posY, environment);
    }

    @Override
    public void action() {
        escapeBro();
    }

    /**
     * Cette fonction permet d'obtenir tout les Hunter
     * 
     * @return la liste de tous les Hunter
     */
    public ArrayList<Hunter> getAllHunter() {
        // First get all Hunter
        ArrayList<Agent> allAgent = environment.agents;
        ArrayList<Hunter> allH = new ArrayList<Hunter>();
        Agent t;
        for (int i = 0; i < allAgent.size(); i++) {
            t = allAgent.get(i);
            if (t.getClass().equals(Hunter.class)) {
                allH.add((Hunter) t);
            }
        }
        return allH;
    }


    public Hunter nearestHunter(ArrayList<Hunter> hunter) {
        int maximumDistance = 500;// la plus grande distance
        int currentDistance; // la distance actuelle
        Hunter ret = null;
        for (int i = 0; i < hunter.size(); i++) {
            currentDistance = hunter.get(i).getPosX() + hunter.get(i).getPosY();
            if (currentDistance < maximumDistance) {
                ret = hunter.get(i);
                maximumDistance = currentDistance;
            }
        }

        return ret;
    }

    /**
     * Escape the nearest Hunter.
     */
    public void escapeBro() {
        ArrayList<Hunter> hunters = getAllHunter();
        Hunter h = nearestHunter(hunters);
        if (h.getPosX() > posX) {// il est a notre droite
            if (h.getPosY() > posY) {
                // a notre droite et plus bas
                if (posY == 0) {
                    if (posX == 0) {
                        // on ne peut rien sinon on se rapprochera de lui
                    } else {
                        posX--;// on se décalle a gauche (avant verifier si case
                               // vide)
                    }
                    // si on est a 0 il faut monter

                } else {
                    posY--;// on monte(avant vérifier si case vide)
                }
            } else {
                // a notre droite et plus bas ou au meme niveau
            }
        } else { // il est a notre gauche ou au meme X
            if (h.getPosX() == posX) {
                // si il est au meme niveau
                if (posY < environment.grid.length) {
                    // on va a droite
                    posY++;// avant verifier si possible
                } else {
                    if (posY > 0) {
                        posY--;// avant verifier si possible
                    } else {
                        posY++;// avant verifier si possible
                    }
                    // sinon il faut essay� de monter

                }
            }

        }
    }
}
