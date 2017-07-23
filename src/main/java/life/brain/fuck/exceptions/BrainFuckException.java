package life.brain.fuck.exceptions;

/**
 * Created by roman on 20.07.17.
 */
public class BrainFuckException extends Exception {
    private String message = "Unhandled exception";

    public BrainFuckException(String message) {
        this.message = message;
    }

    public BrainFuckException() {

    }

    public String getMessage() {
        return this.message;
    }
}
