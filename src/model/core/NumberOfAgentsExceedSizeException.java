package model.core;

public class NumberOfAgentsExceedSizeException extends Exception {
    
    // parameterless Constructor
    public NumberOfAgentsExceedSizeException() {
    }

    // constructor that accepts a message
    public NumberOfAgentsExceedSizeException(String message) {
        super(message);
    }

}
