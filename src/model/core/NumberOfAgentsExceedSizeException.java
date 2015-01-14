package model.core;

public class NumberOfAgentsExceedSizeException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // parameterless Constructor
    public NumberOfAgentsExceedSizeException() {
    }

    // constructor that accepts a message
    public NumberOfAgentsExceedSizeException(String message) {
        super(message);
    }

}
