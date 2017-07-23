package life.brain.fuck.exceptions;

/**
 * Created by roman on 20.07.17.
 */
public class BrainFuckException extends Exception {
    private String message = "uncorrect source code";
    private byte[] interpreterCast;

    public BrainFuckException(String message) {
        this.message = message;
    }

    public BrainFuckException() {

    }

    public BrainFuckException(byte[] interpreterCast) {
        this.interpreterCast = interpreterCast;
    }

    public String getMessage() {
        return this.message;
    }
}
