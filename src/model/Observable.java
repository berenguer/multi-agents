package model;

import view.Observer;

public interface Observable {
    
    public void attach(Observer observeur);
    
    public void remove(Observer observer);
    
    public void notifyObserver();

}
